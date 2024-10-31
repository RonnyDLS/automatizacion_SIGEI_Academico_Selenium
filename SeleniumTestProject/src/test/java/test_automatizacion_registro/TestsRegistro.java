package test_automatizacion_registro;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TestsRegistro {
    static WebDriver driver;
    @BeforeAll
    public static void configuracion(){
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://sigeiacademico.itla.edu.do/account/login");
    }

    // Comprobar cédula invalida
    @Test
    public void CedulaInvalida(){

        // Clic al btn registrate
        WebElement btnRegistrar = driver.findElement(By.xpath("//*[@id=\"kt_body\"]/app-root/ng-component/ng-component/form/div/div/div/div/button"));
        btnRegistrar.click();

        // Hacer click al btn tipo de documento
        WebElement btnDocumentoIdentidad = driver.findElement(By.xpath("//*[@id=\"mat-dialog-0\"]/app-register/div[2]/div/form/div/div[1]/div/ngx-select-dropdown/div/button"));
        btnDocumentoIdentidad.click();

        // Seleccionar el tipo de documento de identidad - Cedula
        WebElement tipoDocumentoCedula = driver.findElement(By.xpath("//*[@id=\"mat-dialog-0\"]/app-register/div[2]/div/form/div/div[1]/div/ngx-select-dropdown/div/div/ul[2]/li[1]"));
        tipoDocumentoCedula.click();

        // Pasar al input de (No. de Documento) una cédula invalida
        WebElement noDocumentoIdentidad = driver.findElement(By.name("txtDocumento"));
        noDocumentoIdentidad.sendKeys("40247755541");

        // Obtener texto de la ventana modal Cédula invalida
        WebElement cedulaInvalida = driver.findElement(By.id("swal2-title"));

        assertEquals("Cédula no Válida, favor ingresela nuevamente.",cedulaInvalida.getText(),
                "El mensaje deberia ser: Cédula no Válida, favor ingresela nuevamente.");

    }

    // Comprobar cédula existente
    @Test
    public void CedulaExistente(){

        // Clic al btn registrate
        WebElement btnRegistrar = driver.findElement(By.xpath("//*[@id=\"kt_body\"]/app-root/ng-component/ng-component/form/div/div/div/div/button"));
        btnRegistrar.click();

        // Hacer click al btn tipo de documento
        WebElement btnDocumentoIdentidad = driver.findElement(By.xpath("//*[@id=\"mat-dialog-0\"]/app-register/div[2]/div/form/div/div[1]/div/ngx-select-dropdown/div/button"));
        btnDocumentoIdentidad.click();

        // Seleccionar el tipo de documento de identidad - Cedula
        WebElement tipoDocumentoCedula = driver.findElement(By.xpath("//*[@id=\"mat-dialog-0\"]/app-register/div[2]/div/form/div/div[1]/div/ngx-select-dropdown/div/div/ul[2]/li[1]"));
        tipoDocumentoCedula.click();

        // Pasar al input de (No. de Documento) una cédula invalida
        WebElement noDocumentoIdentidad = driver.findElement(By.name("txtDocumento"));
        noDocumentoIdentidad.sendKeys("40244530040");

        // Clic fuera del input
        WebElement clicFuera = driver.findElement(By.xpath("//*[@id=\"mat-dialog-0\"]/app-register/div[1]"));
        clicFuera.click();

        // Obtener texto cédula existente
        WebElement cedulaExistente = driver.findElement(By.xpath("//*[@id=\"mat-dialog-0\"]/app-register/div[2]/div/form/div/div[2]/div/span"));

        assertEquals("Estimado usuario, Ya existe un registro con ese mismo numero de Identificación.", cedulaExistente.getText(),
                "El mensaje deberia ser: Estimado usuario, Ya existe un registro con ese mismo numero de Identificación.");

    }

    // Validar requerimientos de la contraseña en Registro
    @Test
    public void ValidarRequerimientosPassword(){

        // Clic al btn registrate
        WebElement btnRegistrar = driver.findElement(By.xpath("//*[@id=\"kt_body\"]/app-root/ng-component/ng-component/form/div/div/div/div/button"));
        btnRegistrar.click();

        // Pasar contraseña al input Contraseña
        WebElement userPassword = driver.findElement(By.name("txtpassword"));
        userPassword.sendKeys("usuariotest");

        // Clic fuera del input
        WebElement clicFuera = driver.findElement(By.xpath("//*[@id=\"mat-dialog-0\"]/app-register/div[1]"));
        clicFuera.click();

        // Obtener texto Contraseña no válida
        WebElement passwordInvalido = driver.findElement(By.xpath("//*[@id=\"mat-dialog-0\"]/app-register/div[2]/div/form/div/div[6]/div/span/div"));
        assertEquals(
                "Contraseña no válida, debe de tener:\n" +
                        "Por lo menos 8 caracteres\n" +
                        "Por lo menos 1 carácter numérico\n" +
                        "Por lo menos 1 letra minúscula\n" +
                        "Por lo menos 1 letra mayúscula\n" +
                        "Por lo menos 1 caracter especial",
                passwordInvalido.getText(),
                "El mensaje deberia ser: Contraseña no válida, debe de tener:\n" +
                        "Por lo menos 8 caracteres\n" +
                        "Por lo menos 1 carácter numérico\n" +
                        "Por lo menos 1 letra minúscula\n" +
                        "Por lo menos 1 letra mayúscula\n" +
                        "Por lo menos 1 caracter especial");
    }

    // Validar contraseña ingresadas no idénticas
    @Test
    public void ValidarPasswordNoIdenticos(){

        // Clic al btn registrate
        WebElement btnRegistrar = driver.findElement(By.xpath("//*[@id=\"kt_body\"]/app-root/ng-component/ng-component/form/div/div/div/div/button"));
        btnRegistrar.click();

        // Pasar contraseña a los input Contraseña
        WebElement userPassword = driver.findElement(By.name("txtpassword"));
        userPassword.sendKeys("#Prueba001");

        // Pasar contraseña al input Confirmar Contraseña
        WebElement confirmPassword = driver.findElement(By.name("txtconfirmPassword"));
        confirmPassword.sendKeys("contraseñaDiferente");

        // Clic fuera del input
        WebElement clicFuera = driver.findElement(By.xpath("//*[@id=\"mat-dialog-0\"]/app-register/div[1]"));
        clicFuera.click();

        // Obtener texto Contraseñas no coinciden
        WebElement passwordNoC = driver.findElement(By.xpath("//*[@id=\"mat-dialog-0\"]/app-register/div[2]/div/form/div/div[7]/div/span"));
        assertEquals("Las contraseñas no coinciden", passwordNoC.getText(),"El mensaje deberia ser: Las contraseñas no coinciden");
    }

    // Validar registro del usuario en el sistema
    @Test
    public void ValidarRegistroUsuario(){

        // Clic al btn registrate
        WebElement btnRegistrar = driver.findElement(By.xpath("//*[@id=\"kt_body\"]/app-root/ng-component/ng-component/form/div/div/div/div/button"));
        btnRegistrar.click();

        // Hacer click al btn tipo de documento
        WebElement btnDocumentoIdentidad = driver.findElement(By.xpath("//*[@id=\"mat-dialog-0\"]/app-register/div[2]/div/form/div/div[1]/div/ngx-select-dropdown/div/button"));
        btnDocumentoIdentidad.click();

        // Seleccionar el tipo de documento de identidad - Pasaporte
        WebElement tipoDocumentoPasaporte = driver.findElement(By.xpath("//*[@id=\"mat-dialog-0\"]/app-register/div[2]/div/form/div/div[1]/div/ngx-select-dropdown/div/div/ul[2]/li[2]"));
        tipoDocumentoPasaporte.click();

        // Pasar al input de (No. de Documento) un Pasaporte
        WebElement noDocumentoIdentidad = driver.findElement(By.name("txtDocumento"));
        noDocumentoIdentidad.sendKeys("PAS140020");

        String correo = "correoprueba88@gmail.com";
        // Pasar correo al input Correo Electrónico y Confirmar Correo Electrónico
        WebElement correoElectronico = driver.findElement(By.name("txtCorreo"));
        correoElectronico.sendKeys(correo);

        // Pasar nombre y apellido a los input nombre y apellido
        WebElement nombreUser = driver.findElement(By.name("txtNombre"));
        nombreUser.sendKeys("Luciano");

        WebElement apellidoUser = driver.findElement(By.name("txtApellido"));
        apellidoUser.sendKeys("Ogando");

        String password_ = "#Prueba001";
        // Pasar contraseña a los input Contraseña y Confirmar Contraseña
        WebElement userPassword = driver.findElement(By.name("txtpassword"));
        userPassword.sendKeys(password_);

        WebElement confirmPassword = driver.findElement(By.name("txtconfirmPassword"));
        confirmPassword.sendKeys(password_);

        // Hacar clic en el botón registrarse
        WebElement btnRegistrase = driver.findElement(By.xpath("//*[@id=\"mat-dialog-0\"]/app-register/div[2]/div/div/button"));
        btnRegistrase.click();

        // Obtener texto Contraseñas no coinciden
        WebElement mensajeAct = driver.findElement(By.xpath("//*[@id=\"swal2-title\"]"));

        assertEquals(
                "Usuario registrado esperando activación, se ha enviado un correo de activación a",
                mensajeAct.getText().substring(0,80),
                "Usuario registrado esperando activación, se ha enviado un correo de activación a");
    }

    @AfterAll
    public static void finalizar (){
        try {
            Thread.sleep(3000);
            driver.quit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
