for /f "tokens=* delims=" %%i in ('dir /s /b /a:d *CVS') do ( rd /s /q "%%i" )