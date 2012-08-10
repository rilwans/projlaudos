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

Set NullDisplay To ''
Set Multilocks On
Set Memowidth To 200
Set Display To Vga25
Set Status Bar On

Set Delete On
Set Safety Off
Set Confirm Off
Set Exclusive On
Set Escape On
Set Separator To "."
Set Point To ","
Set Multilocks On

Set Path To D:\Projetos\SEJUR


Set Century To 19 rollover 30

Set Proc To D:\Projetos\SEJUR\Utils\utils
Public wConecta
wConecta=0

vTpConecta= "banco"



Store SQLConnect("sejur") To wConecta
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


