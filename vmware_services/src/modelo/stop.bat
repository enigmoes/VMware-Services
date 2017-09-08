@echo off

REM .bat con permisos de administrador
:-------------------------------------
REM  --> Analizando los permisos
    IF "%PROCESSOR_ARCHITECTURE%" EQU "amd64" (
>nul 2>&1 "%SYSTEMROOT%\SysWOW64\cacls.exe" "%SYSTEMROOT%\SysWOW64\config\system"
) ELSE (
>nul 2>&1 "%SYSTEMROOT%\system32\cacls.exe" "%SYSTEMROOT%\system32\config\system"
)

REM --> Si hay error es que no hay permisos de administrador.
if '%errorlevel%' NEQ '0' (
    goto UACPrompt
) else ( goto gotAdmin )

:UACPrompt
    echo Set UAC = CreateObject^("Shell.Application"^) > "%temp%\getadmin.vbs"
    set params = %*:"=""
    echo UAC.ShellExecute "cmd.exe", "/c ""%~s0"" %params%", "", "runas", 1 >> "%temp%\getadmin.vbs"

    "%temp%\getadmin.vbs"
    del "%temp%\getadmin.vbs"
    exit /B

:gotAdmin
    pushd "%CD%"
    CD /D "%~dp0"
:--------------------------------------

REM .bat

sc query "VMAuthdService" | find "STATE" | find "START"

IF ERRORLEVEL 0 ( NET STOP "VMAuthdService" )

sc query "VMnetDHCP" | find "STATE" | find "START"

IF ERRORLEVEL 0 ( NET STOP "VMnetDHCP" )

sc query "VMware NAT Service" | find "STATE" | find "START"

IF ERRORLEVEL 0 ( NET STOP "VMware NAT Service" )

sc query "VMUSBArbService" | find "STATE" | find "START"

IF ERRORLEVEL 0 ( NET STOP "VMUSBArbService" )

EXIT
