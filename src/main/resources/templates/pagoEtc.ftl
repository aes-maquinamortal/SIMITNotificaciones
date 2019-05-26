<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Sending Email with Freemarker HTML Template Example</title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>

    <!-- use the font -->
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            font-size: 48px;
        }
    </style>
</head>
<body style="margin: 0; padding: 0;">

    <table align="center" border="0" cellpadding="0" cellspacing="0" width="600" style="border-collapse: collapse;">
        <tr>
            <td align="center" bgcolor="#78ab46" style="padding: 40px 0 30px 0;">
                <h1>Notificación de pago</h1>
            </td>
        </tr>
        <tr>
            <td bgcolor="#eaeaea" style="padding: 40px 30px 40px 30px;">
                <p>Estimado ${name},</p>
                <p>Tu pago mensual ha sido realizado por un valor de ${paymentValue}. Cualquier duda o reclamo hacerla a contacto@simit.org.co.</p>
                <p>Gracias</p>
            </td>
        </tr>
        <tr>
            <td bgcolor="#777777" style="padding: 30px 30px 30px 30px;">
                <p>Dirección Nacional Bogotá, DC Email: contactosimit@fcm.org.co</p> 
				<p>Carrera 7 N° 74 - 64, Piso 10 PBX: (57 + 1) 593 40 20 </p>
				<p>Horario de Atención: 8 a. m. a 5 p. m. Jornada continua</p>
            </td>
        </tr>
    </table>

</body>
</html>
