

Public wusuario, wnivel,wacesso,wusermedico,wnmMedico,wpcadastro,wpcadusu,wpentrada,wpsaida
wnivel = 0
wacesso=0
Wusermedico=0
wusuario=""
wpcadastro=.f.
wpcadusu=.f.
wpentrada=.f.
wpsaida=.f.
Close Databases
Set Proc To \Utils\Utils

On Error Do Form \Utils\p_erro With ;
	ERROR( ), Message( ), Message(1), Program( ), Lineno( )

Public wConecta
Store SQLConnect("sejus") To wConecta

If wConecta> 0
	= SQLExec(wConecta, "SET LANGUAGE portuguese")
Else
	=Messagebox("Ocorreu um erro enquanto a conexão com o servidor SQL estava sendo estabelecida!",16,"Erro de Execução")
	Return
Endif

Set Procedure To \Utils\Utils
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

Public wsistema, wlimpamenu, wtemp,Wnomebusca


IF wnivel = 0
	wnivel=9
ENDIF

wsistema= "SEJUS"
wlimpamenu = .F.
wtemp = "..\TEMPS\"
Wnomebusca=""
Set Sysm To

_Screen.BackColor = Rgb(192,192,192)
_Screen.WindowState = 2
_Screen.Closable = .F.
_Screen.Icon = "clinica.ico"

_Screen.Caption = "Central de Materiais e Medicamentos - SEJUS"
_Screen.AddObject('imagem','Figura')  && Adiciona um controle de figura a um formulário

_Screen.imagem.Visible =.T.



_Screen.Show


DO FORM forms\acesso.scx 



IF wacesso<>0
	Do menuprincipal.mpr
	Read Events
ELSE
	CLEAR EVENTS
endif





Define Class Figura As Image  && Cria a figura
	Picture = "desktop.bmp"
	BackStyle = 0
	Left = 0
	Top = 0
	Height = 778
	Width =  1177
Enddefine


