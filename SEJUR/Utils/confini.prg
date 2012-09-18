Clea All

Public wDt_Limite, wAno, wNr_Operadora, wnivel, wAlteraPaciente, wtemp
Public wnome_arq, wnome_arq1, wnome_arq2, wnome_arq3, wnome_arq4, wnome_arq5
Public windice, windice1, wsistema, wusuario, wsenha, wwRadioterapia
Public wSiglaEmpresaRpt, wNomeEmpresaRpt
Public cConnM2

Public wCep
wCep= ""


Set Date BRITISH

wAno= 2012
wNr_Operadora= 21

wusuario = "Master"
wsistema = "Desenvolvimento"
wnivel = 9
wAlteraPaciente= 0
wtemp = ".\"
wnome_arq = Space(20)
wnome_arq1 = Space(20)
wnome_arq2 = Space(20)
wnome_arq3 = Space(20)
wnome_arq4 = Space(20)
wnome_arq5 = Space(20)
windice = Space(20)
windice1 = Space(20)

*********************************************************
Set Fullpath Off
*********************************************************
set nulldisplay to ''
set multilocks on
set memowidth to 200
set display to vga25
set status bar on

set nulldisplay to ''
set memowidth to 200
set display to vga25
set status bar on
SET CENTURY ON
SET DELETE ON
SET SAFETY OFF
SET TALK OFF
SET NOTIFY OFF
SET CONFIRM OFF
SET CURRENCY TO 'R$ '
SET DATE BRITISH
SET ESCAPE ON
SET EXCLUSIVE OFF
SET SEPARATOR TO "."
SET POINT TO ","
SET MULTILOCKS ON
SET REPROCESS TO AUTOMATIC
SET CENTURY to 19 ROLLOVER 30
SET NULLD to ""
SET SYSFORMATS ON 
SET DECIMALS TO 2
SET REPORTBEHAVIOR 90
SET FULLPATH ON


*SET CURRENCY TO 'R$ '
*SET DATE DMY

Set Path To D:\Projetos\SEJUR


Set Proc To d:\projetos\sejur\utils\utils
Public wConecta
wConecta=0

vTpConecta= "banco"



Store SQLConnect("sejus") To wConecta
Clear



If wConecta> 0
	? "                         Visual FoxPro - Conectado ao Banco de dados"
	= SQLExec(wConecta, "SET LANGUAGE portuguese")
	If vTpConecta= "SQLspccDES"
		?
		? "                                                     *** spccDES ***"
		? "                                                     *** spccDES ***"
		? "                                                     *** spccDES ***"
		? "                                                     *** spccDES ***"
		? "                                                     *** spccDES ***"
		? "                                                     *** spccDES ***"
		? "                                                     *** spccDES ***"
		? "                                                     *** spccDES ***"
		? "                                                     *** spccDES ***"
		? "                                                     *** spccDES ***"
	Endif
Else
	? "                                                     Visual FoxPro - Houve falha ao conectar com SERVER2"
	? "                                                     Visual FoxPro - Houve falha ao conectar com SERVER2"
	? "                                                     Visual FoxPro - Houve falha ao conectar com SERVER2"
	? "                                                     Visual FoxPro - Houve falha ao conectar com SERVER2"
	Return
Endif


