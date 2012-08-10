Lparameters wparam

If Parameters()<> 1
	=Messagebox("Você deve executar o aplicativo SÃO MARCOS para acessar este sistema!",16,"Erro de Execução")
	Return
Endif

Public wusuario, wnivel, waltera_paciente,wusaadm

wusuario= Subst(wparam,1,At('#',wparam)-1)
wnivel = Val(Subst(wparam,At('#',wparam)+1,1))
waltera_paciente= Iif(Isdigit(Right(wparam, 1)), Val(Right(wparam, 1)), 0)
waltera_paciente= Iif(waltera_paciente= 1, 1, 0)
waltera_paciente = Val(Subst(wparam,At('#',wparam,2)+1,1))
wusadm=.T.

Close Databases
Set Proc To ..\Utils\Utils

On Error Do Form ..\Utils\p_erro With ;
	ERROR( ), Message( ), Message(1), Program( ), Lineno( )

Public wConecta
Store SQLConnect("SQLSPCC") To wConecta

If wConecta> 0
	= SQLExec(wConecta, "SET LANGUAGE portuguese")
Else
	=Messagebox("Ocorreu um erro enquanto a conexão com o servidor SQL estava sendo estabelecida!",16,"Erro de Execução")
	Return
Endif

Set Procedure To W:\Utils\Utils
Set Fullpath Off
Set NullDisplay To ''
Set Memowidth To 200
Set Display To Vga25
Set Status Bar On
Set Century On
Set Delete On
Set Safety Off
Set Talk Off
Set Notify Off
Set Confirm Off
Set Currency To 'R$ '
Set Date BRITISH
Set Escape On
Set Exclusive Off
Set Separator To "."
Set Point To ","
Set Multilocks On
Set Reprocess To Automatic
Set Hours To 24
Set Century To 19 ROLLOVER 30
Set NullD To ""
SET NULLDISPLAY TO ""
Set Sysformats On
Set Decimals To 2
SET DATE DMY

Public wsistema, wlimpamenu, wtemp, wSiglaEmpresaRPT, wNomeEmpresaRPT
Public wUserMedico


warea= Select()
= SQLExec(wConecta, "select * from Parametro", "cVer")
wSiglaEmpresaRPT= CVer.sg_empresa
wNomeEmpresaRPT= CVer.nm_empresa
Use In CVer
Select(warea)


warea= Select()
=SQLExec(wConecta, "Select Id_medico From MedUsu Where Nm_Usuario= '" + Allt(wusuario) + "'", "cAux")
Go Top
wUserMedico= Nvl(cAux.Id_medico, 0)
If Used("cAux")
	Use In cAux
Endif
Select(warea)

wsistema= "admissão"
wlimpamenu = .F.
wtemp = "..\TEMPS\"
Set Sysm To

_Screen.BackColor = Rgb(192,192,192)
_Screen.WindowState = 2
_Screen.Closable = .F.
_Screen.Icon = "saomarco.ico"
_Screen.Caption = "APCC - Admissão"
_Screen.AddObject('Logotipo','Figura')  && Adiciona um controle de figura a um formulário
_Screen.AddObject('Hospital','Texto')  && Texto (Hospital)
_Screen.AddObject('Hospital1','Texto1')  && Texto (São Marcos)
_Screen.AddObject('Depart','Texto2')  && Texto (Dep. Informática)

_Screen.Logotipo.Visible =.T.
_Screen.Hospital.Visible =.T.
_Screen.Hospital1.Visible =.T.
_Screen.Depart.Visible =.T.

_Screen.Show

Do ..\admissao\permissao

Do ..\admissao\admissao.mpr

Read Events

Define Class Figura As Image  && Cria a figura
	Picture = "Hsm.bmp"
	BackStyle = 0
	Left = 320
	Top = 180
	Height = 91
	Width = 73
Enddefine

Define Class Texto As Label  && Cria o texto
	Caption = "Admissão"
	FontItalic = .T.
	AutoSize = .T.
	FontSize = 36
	ForeColor = Rgb(0,0,255)
	BackStyle = 0
	Left = 210
	Top = 80
Enddefine

Define Class Texto1 As Label  && Cria o texo
	Caption = "Hospital São Marcos"
	FontItalic = .T.
	AutoSize = .T.
	FontSize = 36
	ForeColor = Rgb(0,0,255)
	BackStyle = 0
	Left = 130
	Top = 300
Enddefine

Define Class Texto2 As Label  && Cria o texo 'Dep. Informática'
	Caption = "Dep. Informática"
	FontItalic = .T.
	AutoSize = .T.
	FontSize = 8
	ForeColor = Rgb(0,0,255)
	BackStyle = 0
	Left = 680
	Top = 480
Enddefine
