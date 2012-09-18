
************************************
FUNCTION CorrTexto(pTexto, pTiraAcento)
  *-- Recebe uma string e retira espaÁos duplicados, pontos, ...

	Local rTexto
	if PARAMETERS()< 2
		pTiraAcento= 1
	endif

	if PARAMETERS()> 0
		if pTiraAcento= 1
			cProcurarPor  = "¡…Õ”⁄·ÈÌÛ˙¿»Ã“Ÿ‡ËÏÚ˘¬ Œ‘€‚ÍÓÙ˚ƒÀœ÷‹‰ÎÔˆ¸√’„ı«Á—Ò∫."
			cSubstituirPor = "AEIOUaeiouAEIOUaeiouAEIOUaeiouAEIOUaeiouAOaoCcNno "
			pTexto = CHRTRAN(pTexto,cProcurarPor,cSubstituirPor)
		else
			if pTiraAcento= 2
				cProcurarPor  = "¡…Õ”⁄·ÈÌÛ˙¿»Ã“Ÿ‡ËÏÚ˘¬ Œ‘€‚ÍÓÙ˚ƒÀœ÷‹‰ÎÔˆ¸√’„ı«Á—Ò"
				cSubstituirPor = "AEIOUaeiouAEIOUaeiouAEIOUaeiouAEIOUaeiouAOaoCcNn"
				pTexto = CHRTRAN(pTexto,cProcurarPor,cSubstituirPor)
			endif
		endif
		rTexto= strtran(strtran(strtran(strtran(strtran(strtran(;
			proper(ltrim(pTexto))," Do "," do "), ;
				" Da ", " da "), ;
				" Dos ", " dos "), ;
				" Das ", " das "), ;
				" De ", " de "), ;
				" E ", " e ")
*		rTexto= strtran(rTexto," Do ", " do ")
*		rTexto= strtran(rTexto," Da ", " da ")
*		rTexto= strtran(rTexto," Dos ", " dos ")
*		rTexto= strtran(rTexto," Das ", " das ")
*		rTexto= strtran(rTexto," De ", " de ")
*		rTexto= strtran(rTexto," E ", " e ")
		do while at("  ", rTexto)> 0
			rTexto= strtran(rTexto, "  ", " ")
		enddo
	endif

	RETURN rTexto
ENDFUNC

************************************
FUNCTION VerErro(pParam)
	*-- Mostra uma mensagem para um possÌvel erro
	*--	O Parametro quando passado tem que ser do tipo char
	*   e serve para ajudar a localizar o programa e a linha do comando gerador do erro

	vDeuErro= AERROR(aEA)
	if vDeuErro> 0	
		if type("pParam")= "L"
			pParam= null
		endif

		if wConecta> 0
			= sqlexec(wConecta, "IF @@TRANCOUNT> 0 ROLLBACK TRAN")
		endif

		= sqlexec(wConecta, "insert into Advertencia " + ;
			"(Sistema, Usuario, Instante, Nr_Erro, Mensagem, Complemento, Area, Tipo, Parametro) " + ;
			"values ('" + wSistema + "', '" + wUsuario + "', getdate(), " + str(nvl(aEA[1], 0), 9) + ;
			", '" + strtran(nvl(aEA[2], ""), "'", '"') + "', '" + strtran(nvl(aEA[3], "Sem"), "'", '"') + "', " + ;
			nvl(aEA[4], "null") + ", '" + ;
			alltrim(nvl(str(aEA[5], 9), "")) + " - " + iif(!between(nvl(aEA[5], 0), 1, 3), "N„o foi de trigger", ;
				iif(nvl(aEA[5], 0)= 1, "Insert", iif(nvl(aEA[5], 0)= 2, "Update", "Delete")) + " trigger failed") + ;
				"', '" + nvl(pParam, "#1# " + alltrim(PROGRAM(1)) + " #2# " + alltrim(PROGRAM(2)) + ;
				" #3# " + alltrim(PROGRAM(3)) ) + "')")

		do case
			case at("**", aEA[2])> 0 
				MessageBox(substr(aEA[2], at("**", aEA[2]) + 2, AT('"',substr(aEA[2],at("**", aEA[2]),500))), 48, ;
					"AtenÁ„o! (NÌvel de Classe)")
			case aEA[1]= 1526 and at("NULL into column ", aEA[2])> 0 
				MessageBox("Para que o registro seja incluÌdo, o campo" + chr(13) + ;
				substr(aEA[2], at("NULL into column ", aEA[2]) + 18, ;
					at("', table", aEA[2]) - at("NULL into column ", aEA[2]) - 18) + ;
					" tem que estar devidamente preenchido!", 48, ;
					"AtenÁ„o! (NÌvel de Classe)")
			case aEA[1]= 1526 and at("DELETE statement conflicted with ", aEA[2])> 0 
				MessageBox("O registro n„o pode ser excluido porque esta sendo usado na tabela " + ;
				substr(aEA[2], at(", table ", aEA[2]) + 8, 50), 48, ;
					"AtenÁ„o! (NÌvel de Classe)")
			case aEA[1]= 1526 and at("Violation of PRIMARY KEY ", aEA[3])> 0 
				MessageBox("N„o È possÌvel gravar porque foi violada a chave ˙nica da tabela " + ;
				substr(aEA[3], at("in object ", aEA[3]) + 10, 50), 48, ;
					"AtenÁ„o! (NÌvel de Classe)")
			case aEA[1]= 1526 and at("in an out-of-range datetime value", aEA[3])> 0 
				MessageBox("N„o È possÌvel gravar porque a hora (ou a data) informada n„o È v·lida!", 48, "AtenÁ„o! (NÌvel de Classe)")
			OTHERWISE
				MessageBox(alltrim(str(nvl(aEA[1], ""), 10)) + " - " + nvl(aEA[2], "") + chr(13) + ;
					nvl(aEA[3], ""), 48, "AtenÁ„o! (NÌvel de Classe)")
		endcase
	endif

	return
ENDFUNC

************************************
FUNCTION ChecaCPF(Num_CPF)
	*-- Recebe um n˙mero de CPF e verifica se o mesmo È v·lido
	
	wcpf = Num_CPF
	
	soma = 0
	for i= 1 to 9
		soma = soma + Val(subst(wcpf,i,1)) * (11-i)
	endfor
	
	resto = 11 - (soma - (int(soma/11)*11))
	if resto = 10 or resto = 11
		resto = 0
	endif
	
	if resto <> val(subst(wcpf,10,1))
		Return(.F.)
	endif	
	
	soma= 0
	for i= 1 to 10
		soma= soma + Val(subst(wcpf,i,1)) * (12-i)
	endfor
	
	resto = 11 - (soma - (int(soma/11)*11))
	if resto = 10 or resto = 11
		resto = 0
	endif

	if resto <> val(subst(wcpf,11,1))
		Return (.F.)
	endif
	
	Return(.T.)
ENDFUNC

******************************************
FUNCTION Dia(Data)
	Do Case
		Case Dow(Data)= 0 or Dow(Data)= 1
			Return("Domingo")
		Case Dow(Data)= 2
			Return("Segunda-Feira")
		Case Dow(Data)= 3
			Return("TerÁa-Feira")
		Case Dow(Data)= 4
			Return("Quarta-Feira")
		Case Dow(Data)= 5
			Return("Quinta-Feira")
		Case Dow(Data)= 6
			Return("Sexta-Feira")
		Case Dow(Data)= 7
			Return("S·bado")
	EndCase
ENDFUNC

******************************************
FUNCTION Mes(Data)
	Do Case
		Case Month(Data)= 1
			Return("Janeiro")
		Case Month(Data)= 2
			Return("Fevereiro")
		Case Month(Data)= 3
			Return("MarÁo")
		Case Month(Data)= 4
			Return("Abril")
		Case Month(Data)= 5
			Return("Maio")
		Case Month(Data)= 6
			Return("Junho")
		Case Month(Data)= 7
			Return("Julho")
		Case Month(Data)= 8
			Return("Agosto")
		Case Month(Data)= 9
			Return("Setembro")
		Case Month(Data)= 10
			Return("Outubro")
		Case Month(Data)= 11
			Return("Novembro")
		Case Month(Data)= 12
			Return("Dezembro")
	EndCase
ENDFUNC

******************************************
FUNCTION Encrypt()
	* Somente para eliminar o erro no momento da compilaÁ„o
ENDFUNC

******************************************
FUNCTION Decrypt()
	* Somente para eliminar o erro no momento da compilaÁ„o
ENDFUNC

******************************************
FUNCTION Extenso(M_Valor)
	* Programa : Extenso <Para transformar o valor numerico em valor por extenso>

	*
	M_Val1='1Hum         2Dois        3TrÍs        4Quatro      5Cinco       6Seis        7Sete        8Oito        9Nove       '
	M_Val2='1Onze        2Doze        3Treze       4Quatorze    5Quinze      6Dezesseis   7Dezessete   8Dezoito     9Dezenove   '
	M_Val3='1Dez         2Vinte       3Trinta      4Quarenta    5Cinquenta   6Sessenta    7Setenta     8Oitenta     9Noventa    '
	M_Val4='1Cem         2Duzentos    3Trezentos   4Quatrocentos5Quinhentos  6Seiscentos  7Setecentos  8Oitocentos  9Novecentos '
	*
	M_Var=1
	M_Seq=1
	M_Ext=Spac(01)
	*
	Do While M_Seq <= 15
	   If M_Seq = 13
	      M_Seq=14
	   Endif
	*
	   Do Case
	      Case M_Seq = 3 .or. M_Seq = 6 .or. M_Seq = 9 .or. M_Seq = 12 .or. M_Seq = 15
	           M_Val='M_Val1'
	      Case M_Seq = 1 .or. M_Seq = 4 .or. M_Seq = 7 .or. M_Seq = 10
	           M_Val='M_Val4'
	      Case M_Seq = 2 .or. M_Seq = 5 .or. M_Seq = 8 .or. M_Seq = 11 .or. M_Seq = 14
	           If Subs(Str(M_Valor,15,2),M_Seq,1) = '0'
	              M_Seq=M_Seq+1
	              M_Val='M_Val1'
	           Else
	             If Subs(Str(M_Valor,15,2),M_Seq,1) = '1' .and. Val(Subs(Str(M_Valor,15,2),M_Seq+1,1)) > 0
	                M_Val='M_Val2'
	                M_Seq=M_Seq+1
	             Else
	                M_Val='M_Val3'
	             Endif
	           Endif
	   EndCase
	*
	   If Val(Subs(Str(M_Valor,15,2),M_Seq,1)) > 0
	      M_Sequ=1
	      M_Var =1
	      Do While M_Sequ <= 9
	         If Subst(str(M_Valor,15,2),M_Seq,1) = Subst(&M_Val,M_Var,1)
	            M_Var=M_Var+1
	            If M_Val = 'M_Val4' .and. Subst(str(M_Valor,15,2),M_Seq,1) = '1' .and. Subst(str(M_Valor,15,2),M_Seq+1,2) # '00'
	               If Len(Trim(M_Ext)) # 0
	                  M_ext=M_Ext+' e Cento '
	               Else
	                  M_ext=M_Ext+' Cento '
	               Endif
	            Else
	               If Len(Trim(M_Ext)) # 0
	                  M_ext=M_Ext+' e '+Trim(Subst(&M_Val,M_Var,12))
	               Else
	                  M_ext=M_Ext+' '+Trim(Subst(&M_Val,M_Var,12))
	               Endif
	            Endif
	            Exit
	         Endif
	         M_Sequ=M_Sequ+1
	         M_Var=M_Var+13
	      Enddo
	   Endif
	*
	   If M_Seq = 3
	      If Val(Subs(Str(M_Valor,15,2),1,15)) > 999999999.99 .and. Val(Subs(Str(M_Valor,15,2),1,15)) < 1999999999.99
	         M_Ext=M_Ext+' Bilh„o '
	      Else
	        If Val(Subs(Str(M_Valor,15,2),1,15)) > 1999999999.99
	           M_Ext=M_Ext+' Bilhıes '
	        Endif
	      Endif
	   Else
	     If M_Seq = 6
	        If Val(Subs(Str(M_Valor,15,2),4,12)) > 999999.99 .and. Val(Subs(Str(M_Valor,15,2),4,12)) < 1999999.99
	           M_Ext=M_Ext+' Milh„o '
	        Else
	          If Val(Subs(Str(M_Valor,15,2),4,12)) > 1999999.99
	             M_Ext=M_Ext+' Milhıes '
	          Endif
	        Endif
	     Else
	       If M_Seq = 9
	          If Val(Subs(Str(M_Valor,15,2),7,9)) > 999.99
	             M_Ext=M_Ext+' Mil '
	          Endif
	       Else
	         If M_Seq = 12
	            If Val(Subs(Str(M_Valor,15,2),10,6)) > 0.99 .and. Val(Subs(Str(M_Valor,15,2),10,6)) < 1.99
	               M_Ext=M_Ext+' Real '
	            Else
	              If Val(Subs(Str(M_Valor,15,2),1,15)) > 1.99
	                 M_Ext=M_Ext+' Reais'
	              Endif
	            Endif
	         Else
	           If M_Seq = 15
	              If Val(Subs(Str(M_Valor,15,2),14,2)) > 0 .and. Val(Subs(Str(M_Valor,15,2),14,2)) < 2
	                 M_Ext=M_Ext+' Centavo'
	              Else
	                If Val(Subs(Str(M_Valor,15,2),14,2)) > 0
	                   M_Ext=M_Ext+' Centavos'
	                Endif
	              Endif
	           Endif
	         Endif
	       Endif
	     Endif
	   Endif
	*
	   M_Seq = M_Seq + 1
	*
	Enddo

	return M_Ext
ENDFUNC

Function CapImpDefa
Declare long GetProfileString in "kernel32" ;
	string lpAppName, string lpKeyName, string lpDefault, ;
	string lpReturnedString, long nSize

*
* Pega Impressora Default
*
printer_default = space(1024)
len_ret = GetProfileString("Windows","device","",@printer_default,;
	len(printer_default))
printer_default = left(printer_default,AT(",",printer_default)-1)

return printer_default

FUNCTION SetaImpDefa(imp,muda)
Declare long WriteProfileString in "kernel32" ;
	string lpszSection, string lpszKeyName, string lpszString 

Declare long SendMessage in "user32" ;
	long hwnd, long wMSg, long wParam, String lParam

if muda
	HWND_BROADCAST = -1
	WM_WININICHANGE = 26
endif	
*
* Seta Impressora Default
*
* Nome da Impressora 
printer_name   = imp
printer_buffer = space(200)
*
len_ret        = GetProfileString("PrinterPorts", printer_name, "", ;
	@printer_buffer, Len(printer_buffer))
printer_buffer = left(printer_buffer,len_ret)
*
printer_driver = left(printer_buffer,at(',',printer_buffer)-1)
printer_buffer = substr(printer_buffer,at(',',printer_buffer)+1)
*
printer_port   = left(printer_buffer,at(',',printer_buffer)-1)
*
new_printer_default = printer_name + "," + printer_driver + "," + printer_port
updated = WriteProfileString("Windows","device",new_printer_default)
*
if muda and updated = 1
	SendMessage(HWND_BROADCAST,WM_WININICHANGE,0,"windows")
endif

return updated

endfunc

************************************
FUNCTION DataServidor()
	*-- Retorna a data do servidor

	Local wSel, rData
	
	wSel= select()
	= sqlexec(wConecta, "select getdate() as Data", "cData")
	rData= ctod(dtoc(cData.Data))
	select(wSel)
	
	return rData
ENDFUNC

************************************
FUNCTION HoraServidor()
	*-- Retorna a Hora do servidor

	Local wSel, rHora
	
	wSel= select()
	= sqlexec(wConecta, "select getdate() as Hora", "cHora")
	rHora= right(ttoc(cHora.Hora), 8)
	select(wSel)
	
	return rHora
ENDFUNC

************************************
FUNCTION AbreviaNome(M_NOME, M_TAM)
	M_NOME= UPPER(M_NOME)
	LOCAL wCount1, wNovoNome
	wCount1= 1
	wNovoNome= ''

	IF LEN(ALLTRIM(M_NOME)) > M_TAM
		M_NOME = STRTRAN(STRTRAN(STRTRAN(STRTRAN(STRTRAN(STRTRAN(M_NOME, ' DO ', ' '), ;
			' DA ', ' '), ' DE ', ' '), ' DOS ', ' '), ' DAS ', ' '), ' E ', ' ')
		DO WHILE LEN(ALLT(M_NOME)) > 0
			IF AT(' ', M_NOME) > 0
				wNovoNome = wNovoNome + SUBSTR(M_NOME, 1, IIF(wCount1 = 1, AT(' ', M_NOME) - 1, 1)) + ' '
				M_NOME = SUBSTR(ALLTRIM(M_NOME), AT(' ', M_NOME) + 1, LEN(ALLTRIM(M_NOME)) - AT(' ', M_NOME))
			ELSE
				wNovoNome = wNovoNome + ALLTRIM(M_NOME)
				M_NOME = ''
			ENDIF
			wCount1 = wCount1 + 1
		ENDDO
	ENDIF
	RETURN wNovoNome
ENDFUNC

************************************
FUNCTION TiraAcento(pTexto)
  *-- Recebe uma string e retira espaÁos duplicados, pontos, ...

	Local rTexto
	if PARAMETERS()> 0
		cProcurarPor  = "¡…Õ”⁄·ÈÌÛ˙¿»Ã“Ÿ‡ËÏÚ˘¬ Œ‘€‚ÍÓÙ˚ƒÀœ÷‹‰ÎÔˆ¸√’„ı«Á—Ò"
		cSubstituirPor = "AEIOUaeiouAEIOUaeiouAEIOUaeiouAEIOUaeiouAOaoCcNn"
		pTexto = CHRTRAN(pTexto,cProcurarPor,cSubstituirPor)
		rTexto= strtran(strtran(strtran(strtran(strtran(strtran(;
			proper(ltrim(pTexto))," Do "," do "), ;
				" Da ", " da "), ;
				" Dos ", " dos "), ;
				" Das ", " das "), ;
				" De ", " de "), ;
				" E ", " e ")
	endif

	RETURN rTexto
ENDFUNC

************************************
function RPTSKIP (ctipobanda)
	if ctipobanda= 1
		SELECT (ALIAS())
		SKIP
	else
		if eof(alias())
			go bott
		endif
	endif
endfunc
*************************************
*********************************************************************************************** 
* Nome       : CalculaIdade() 
* Tipo       : FunÁ„o 
* Chamada por: V·rios 
* Par‚metros : <ExprD1> Data de Nascimento; <ExprD2> Data atual ou atÈ a data desejada; <ExprN1> Tipo de Retorno 
* <ExprD1> Data de Nascimento; 
* <ExprD2> Data atual ou atÈ a data desejada; 
* <ExprN1> Tipo de Retorno: 1=Somente Ano; 2=Somente Meses; 3=Somente Dias; 4=Anos, meses e dias (aa mm dd) 
* Executa    : C·lculo da idade 
* Retorna    : Idade no formato especificado 
* Notas      : Nenhuma 
* Autor      : Walter Carlin Junior, (c) 2002, Cyber Systems Tecnologia Ltda. 
*********************************************************************************************** 
Function CalculaIdade 
Parameters dDataNasc, dDataAtual, nTipoRetorno 
Local anos, meses, dias 
Store 0 To anos, meses, dias 
dini = dDataNasc 
dfim = dDataAtual 

If Empty(dini) 
 Do Case 
 Case nTipoRetorno == 1   && Somente Ano 
    Return 0 
 Case nTipoRetorno == 2   && Somente Meses 
    Return 0 
 Case nTipoRetorno == 3   && Somente Dias 
    Return 0 
 Case nTipoRetorno == 4   && Anos e Meses (String) 
    Return "" 
 EndCase 
Endif 

mesdianas  = Subs(DTOS(dini), 5) 
mesdiahoje = Subs(DTOS(dfim), 5) 

**--{ Achar ano }--------- 
If mesdiahoje >= mesdianas 
   anos  = Year(dfim) - Year(dini) 
Else 
   anos  = Year(dfim) - Year(dini) - 1 
Endif 

**--{ Achar mÍs }--------- 
meses = 0 
*ddata = ctod(subs(dtoc(dini), 1, 6) + str(year(dfim))) 
If Month(DIni) = 2 And Day(DIni) = 29 && anivers·rio no ano Bissexto  
	ddata = ctod(substr(dtoc(dini-1), 1, 6) + Str(Year(dfim))) 
Else  
	ddata = ctod(substr(dtoc(dini), 1, 6) + Str(Year(dfim))) 
Endif 

Do While Month(ddata) # Month(dfim) 
   meses = meses + 1 
   ddata = Gomonth(ddata, 1) 
Enddo 

If meses = 0 
   if day(dini) > day(dfim) 
      meses = 11 
   endif 
endif 
If Day(dini) < Day(dfim) 
   dias = Day(dfim) - Day(dini) 
Else 
   If Day(dini) == Day(dfim) 
      dias = 0 
   Else 
    n1 = Day(Ctod("01/"+Subs(DTOC(dfim), 4))-1) 
  dias = n1 - Day(dIni) + Day(dfim) 
 Endif 
Endif 

Do Case 
Case nTipoRetorno == 1   && Somente Ano 
	Return anos 
Case nTipoRetorno == 2   && Somente Meses 
	Return meses 
Case nTipoRetorno == 3   && Somente Dias 
	Return dias 
Case nTipoRetorno == 4   && Anos, Meses e dias (String) 
	Return AllTrim(Str(anos))+"a " + AllTrim(Str(meses))+ "m " + AllTrim(Str(dias))+ "d" 
Case nTipoRetorno == 5
	if Anos > 0
		Return allt(str(anos)) + 'A'
	else
		if Meses > 0
			Return allt(str(meses)) + 'M'
		else
			Return allt(str(dias)) + 'D'
		endif
	endif
ENDCASE

FUNCTION IsNumericUtils
PARAMETERS dString
LOCAL pPosicao, pNumerico
FOR pPosicao= 1 TO LEN(ALLTRIM(dString))
	IF !ISDIGIT(SUBSTR(dString,pPosicao,1))
		RETURN .f.
	ENDIF
ENDFOR
RETURN .t.
ENDFUNC

**Retorna um inteiro convertido para string e sem espaÁos
FUNCTION AllStr(pInt)
	RETURN ALLTRIM(STR(pInt))
ENDFUNC

**Retorna 
FUNCTION split(pString,pAt,nParametro)
	* pString: String da pesquisa
	* pAt: caractere de quebra
	*nParametro: N˙mero do Par‚metro
	*Retorno: String
	pos = AT(pAt, pString)
	RETURN SUBSTR( pString, IIF(nParametro=1,1,pos+1), pos-1 )
ENDFUNC
