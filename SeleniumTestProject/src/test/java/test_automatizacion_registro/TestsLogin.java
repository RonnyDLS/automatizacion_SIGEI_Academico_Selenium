package test_automatizacion_registro;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestsLogin {

    static WebDriver driver;

    // Configurando driver y tamaño del browser
    @BeforeAll
    public static void config (){
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.get("https://sigeiacademico.itla.edu.do/account/login");
    }

    // Validar mensaje cuando el usuario no existe
    @Test
    public void MensajeUsuarioInexistente(){

        // Introducir email (Usuario)
        WebElement usuarioInput = driver.findElement(By.id("email"));
        usuarioInput.sendKeys("usuarioinexistente@gmail.com");

        // Introducir contraseña
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("lAS14789@LL");

        // Dar click al boton Iniciar sesión
        WebElement btnLogin = driver.findElement(By.id("btnLogin"));
        btnLogin.click();

        /// Intacia de tiempo
        WebDriverWait tiempo = new WebDriverWait(driver, Duration.ofSeconds(5));
        /// Esperar a que aparezca el mensaje
        tiempo.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"toast-container\"]/div")));

        /// Error
        WebElement error = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div[1]"));
        /// Mensaje
        WebElement mensaje = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div[2]"));

        // Capturar mensaje de error
        String errorMensaje = error.getText() +" "+ mensaje.getText();

        // =================== ASERCIÓN ===================

        assertEquals("¡Ha ocurrido un error inesperado! El usuario indicado no existe", errorMensaje,
                "Error: El mensaje deberia ser: \"¡Ha ocurrido un error inesperado! El usuario indicado no existe\"");

    }

    // Validar mensajes cuando las crenciales no se envian
    @Test
    public void MensajeCredencialesRequeridas(){

        // Dar click al boton Iniciar sesión
        WebElement btnLogin = driver.findElement(By.id("btnLogin"));
        btnLogin.click();

        // Instacia de tiempo
        WebDriverWait tiempoMsjUsuario = new WebDriverWait(driver, Duration.ofSeconds(2));
        // Esperar hasta que el mensaje sea visible
        tiempoMsjUsuario.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"kt_body\"]" +
                "/app-root/ng-component/ng-component/form/div/div/div/div/div[1]/div[2]/div")));

        // Mensaje del campo usuario
        WebElement msjUsuario = driver.findElement(By.xpath("//*[@id=\"kt_body\"]/app-root/ng-component/ng-" +
                "component/form/div/div/div/div/div[1]/div[2]/div"));

        // Instacia de tiempo
        WebDriverWait tiempoPassUsuario = new WebDriverWait(driver, Duration.ofSeconds(2));
        // Esperar hasta que el mensaje sea visible
        tiempoPassUsuario.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"kt_body\"]" +
                "/app-root/ng-component/ng-component/form/div/div/div/div/div[2]/div[2]/div")));

        // Mensaje del campo contraseña
        WebElement passUsuario = driver.findElement(By.xpath("//*[@id=\"kt_body\"]/app-root/ng-component/" +
                "ng-component/form/div/div/div/div/div[2]/div[2]/div"));


        // =================== ASERCIONES ===================

        // Comprobar si el mesaje de usuario es el esperado
        assertEquals("Debe indicar su correo electrónico", msjUsuario.getText(), "El mensaje debería ser: Debe " +
                "indicar su correo electrónico");

        // Comprobar si el mesaje de la contraseña es el esperado
        assertEquals("Debe colocar su contraseña", passUsuario.getText(), "El mensaje debería ser: Debe colocar " +
                "su contraseña");
    }

    // Validar mensaje cuando el campo de usuario se mande vacio
    @Test
    public void MesajeCampoUsarioVacio(){

        // Introducir contraseña
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("lAS14789@LL");

        // Dar click al boton Iniciar sesión
        WebElement btnLogin = driver.findElement(By.id("btnLogin"));
        btnLogin.click();

        // Instacia de tiempo
        WebDriverWait tiempoMsjUsuario = new WebDriverWait(driver, Duration.ofSeconds(2));
        // Esperar hasta que el mensaje sea visible
        tiempoMsjUsuario.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"kt_body\"]" +
                "/app-root/ng-component/ng-component/form/div/div/div/div/div[1]/div[2]/div")));

        // Mensaje del campo usuario
        WebElement msjUsuario = driver.findElement(By.xpath("//*[@id=\"kt_body\"]/app-root/ng-component/ng-" +
                "component/form/div/div/div/div/div[1]/div[2]/div"));


        // =================== ASERCIÓN ===================

        // Comprobar si el mesaje de usuario es el esperado
        assertEquals("Debe indicar su correo electrónico", msjUsuario.getText(), "El mensaje debería ser: Debe " +
                "indicar su correo electrónico");

    }

    // Validar mensaje cuando el campo de la contraseña se mande vacio
    @Test
    public void MesajeCampoPasswordVacio(){

        // Introducir email (Usuario)
        WebElement usuarioInput = driver.findElement(By.id("email"));
        usuarioInput.sendKeys("zimlidirzo@gufum.com");

        // Dar click al boton Iniciar sesión
        WebElement btnLogin = driver.findElement(By.id("btnLogin"));
        btnLogin.click();

        // Instacia de tiempo
        WebDriverWait tiempoPassUsuario = new WebDriverWait(driver, Duration.ofSeconds(2));
        // Esperar hasta que el mensaje sea visible
        tiempoPassUsuario.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"kt_body\"]" +
                "/app-root/ng-component/ng-component/form/div/div/div/div/div[2]/div[2]/div")));

        // Mensaje del campo contraseña
        WebElement passUsuario = driver.findElement(By.xpath("//*[@id=\"kt_body\"]/app-root/ng-component/" +
                "ng-component/form/div/div/div/div/div[2]/div[2]/div"));


        // =================== ASERCIÓN ===================

        // Comprobar si el mesaje de la contraseña es el esperado
        assertEquals("Debe colocar su contraseña", passUsuario.getText(), "El mensaje debería ser: Debe colocar " +
                "su contraseña");

    }

    // Validar mensaje de las credenciales incorrectas
    @Test
    public void MensajeCredencialesIncorrectas(){

        // Introducir email (Usuario)
        WebElement usuarioInput = driver.findElement(By.id("email"));
        usuarioInput.sendKeys("delossantosr835@gmail.com");

        // Introducir contraseña
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("lAS14789@LL");

        // Dar click al boton Iniciar sesión
        WebElement btnLogin = driver.findElement(By.id("btnLogin"));
        btnLogin.click();

        /// Intacia de tiempo
        WebDriverWait tiempo = new WebDriverWait(driver, Duration.ofSeconds(5));
        /// Esperar a que aparezca el mensaje
        tiempo.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"toast-container\"]/div")));

        /// Error
        WebElement error = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div[1]"));
        /// Mensaje
        WebElement mensaje = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div[2]"));

        // Capturar mensaje de error
        String errorMensaje = error.getText() +" "+ mensaje.getText();

        // =================== ASERCIÓN ===================

        assertEquals("¡Ha ocurrido un error inesperado! Usuario o contraseña inválido. Por favor verificar.", errorMensaje,
                "Error: El mensaje deberia ser: \"¡Ha ocurrido un error inesperado! Usuario o contraseña inválido. Por favor verificar.\"");

    }

    // Validar que el usuario inicie sesión con sus credenciales
    @Test
    public void ValidarCredenciales(){

        // Introducir email (Usuario)
        WebElement usuarioInput = driver.findElement(By.id("email"));
        usuarioInput.sendKeys("sespesordo@gufum.com");

        // Introducir contraseña
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("lAS14789@");

        // Dar click al boton Iniciar sesión
        WebElement btnLogin = driver.findElement(By.id("btnLogin"));
        btnLogin.click();

        // Tiempo de espera
        WebDriverWait tiempo = new WebDriverWait(driver, Duration.ofSeconds(5));
        tiempo.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"kt_content\"]/div/div/div/div/div/div[2]/div/div[1]/div/div/h2")));

        // Titulo lobby
        WebElement tituloLobby = driver.findElement(By.xpath("//*[@id=\"kt_content\"]/div/div/div/div/div/div[2]/div/div[1]/div/div/h2"));

        // =================== ASERCIÓN ===================

        assertTrue(tituloLobby.isDisplayed(), "Las credenciales son incorrectas");
    }

    // Cerrar el navegador
    @AfterAll
    public static void finalizar(){
        int segundos = 5;
        try {
            Thread.sleep(segundos+000);
        } catch (Exception e) {
            System.out.println("No se pudo detener el hilo por "+segundos+" segundos");
            System.out.println("Error: "+e);
        }
        driver.quit();
    }

}
