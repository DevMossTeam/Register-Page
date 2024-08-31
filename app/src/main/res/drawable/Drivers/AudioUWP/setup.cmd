set path=%~DP0
set Displayname=Realtek
for /f "delims=" %%i in ('dir /a-d /b /on %path%\*.appxbundle') do set AppxName=%path%%%i

for /f "delims=" %%i in ('dir /a-d /b /on %path%\*.xml') do set Licnesexml=%path%%%i

for /f "delims=" %%i in ('dir /a-d /b /on %path%\Microsoft.VCLibs.140.00_*_x64_*.appx') do set vclibs=%path%%%i

C:\Windows\system32\DISM /Online /Add-ProvisionedAppxPackage /PackagePath:%AppxName% /LicensePath:%Licnesexml% /DependencyPackagePath:%vclibs% /region=all
IF %ERRORLEVEL% == 0 GOTO :APPX_Check
IF %ERRORLEVEL% == 0 GOTO :APPX_Check

echo Install Realtek FAIL
echo Install Realtek FAIL >> C:\SWWORK\IPGLOGS\Modules.log
EXIT /B1

:APPX_Check
C:\Windows\system32\DISM /online /get-provisionedappxpackages | C:\Windows\system32\Find /i "%Displayname%"
IF %ERRORLEVEL% == 0 GOTO :END
GOTO :APPX_Fail

:APPX_Fail
ECHO %DATE% %TIME% [%AUMID%] APPX CHECK FAIL,ErrorLevel=%ERRORLEVEL% !!!!!! >> c:\swwork\modules.log
EXIT /B1

:end
EXIT /B0