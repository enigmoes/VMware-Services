#define MyAppName "VMware Services"
#define MyAppVersion "2.1.0"
#define MyAppPublisher "Enigmo"
#define MyAppExeName "VMware-Services.exe"

[Setup]
AppId={{A3F2D1E0-7B4C-4A2F-9D8E-1F5C3E7A6B90}
AppName={#MyAppName}
AppVersion={#MyAppVersion}
AppVerName={#MyAppName} v{#MyAppVersion}
AppPublisher={#MyAppPublisher}
DefaultDirName={autopf}\{#MyAppName}
DefaultGroupName={#MyAppName}
DisableProgramGroupPage=yes
OutputDir=C:\PWA\VMware-Services\out
OutputBaseFilename=VMware-Services-Setup-v{#MyAppVersion}
SetupIconFile=C:\PWA\VMware-Services\src\images\vms_100.ico
Compression=lzma2/ultra64
SolidCompression=yes
WizardStyle=modern
PrivilegesRequired=admin
ArchitecturesInstallIn64BitMode=x64compatible
UninstallDisplayIcon={app}\{#MyAppExeName}
UninstallDisplayName={#MyAppName} v{#MyAppVersion}

[Languages]
Name: "spanish"; MessagesFile: "compiler:Languages\Spanish.isl"

[Tasks]
Name: "desktopicon"; Description: "Crear acceso directo en el escritorio"; GroupDescription: "Iconos adicionales:"; Flags: unchecked

[Files]
; Ejecutable principal (generado por Launch4j)
Source: "C:\PWA\VMware-Services\out\{#MyAppExeName}"; DestDir: "{app}"; Flags: ignoreversion

; JRE bundled (no requiere Java instalado en el sistema)
Source: "C:\PWA\VMware-Services\out\jre\*"; DestDir: "{app}\jre"; Flags: ignoreversion recursesubdirs createallsubdirs

[Icons]
; Menú inicio
Name: "{group}\{#MyAppName}"; Filename: "{app}\{#MyAppExeName}"; IconFilename: "{app}\{#MyAppExeName}"
Name: "{group}\Desinstalar {#MyAppName}"; Filename: "{uninstallexe}"

; Escritorio (solo si el usuario marcó la tarea)
Name: "{autodesktop}\{#MyAppName}"; Filename: "{app}\{#MyAppExeName}"; IconFilename: "{app}\{#MyAppExeName}"; Tasks: desktopicon

[Run]
Filename: "{app}\{#MyAppExeName}"; Description: "Ejecutar {#MyAppName}"; Flags: nowait postinstall skipifsilent
