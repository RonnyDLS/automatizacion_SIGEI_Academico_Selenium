package test_automatizacion_registro;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestsAdmision {

    static WebDriver driver;

    @BeforeAll
    public static void config(){
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://qasigeiacademico.itla.edu.do/account/login");
    }

    // Validar advertencia – Crear solicitud con campos vacíos
    @Test
    public void SolicitudCamposRequeridos(){

        // Introducir email (Usuario)
        WebElement usuarioInput = driver.findElement(By.id("email"));
        usuarioInput.sendKeys("bussoifoiteimmou-2816@yopmail.com");

        // Introducir contraseña
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("lAS14789@");

        // Dar click al boton Iniciar sesión
        WebElement btnLogin = driver.findElement(By.id("btnLogin"));
        btnLogin.click();

        try {
            Thread.sleep(4000);
        }catch (Exception e){

        }

        // Hacer clic al boton "ADMISIÓN TECNOLOGO"
        WebElement btnAdmision = driver.findElement(By.xpath("//*[@id=\"kt_content\"]/div/div/div/div/div/div[2]/div[2]/div/div[1]/div[1]/a/div/div[1]/div[2]/div[3]"));
        btnAdmision.click();

        // Hacer clic al boton "Realizar Solicitud"
        WebElement solicitud= driver.findElement(By.id("btnAdd"));
        solicitud.click();

        // Crear tiempo
        tiempo(5000);

        // Hacer clic en el boton "Guardar"
        WebElement guardar= driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[3]/button[2]/span"));
        guardar.click();

        // Hacer clic en el boton "OK" de la ventana modal
        WebElement ok= driver.findElement(By.xpath("//*[@id=\"kt_body home-modal\"]/div[2]/div/div[6]/button[1]"));
        ok.click();

        // Lista de los campos en Solicitud
        List<WebElement> listaDatosSolicitud = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[1]/div"));

        List<WebElement> listaDatosSolicitudRequeridos = new ArrayList<WebElement>(); // Lista de los campos requeridos en Solicitud
        List<WebElement> listaDatosSolicitudNoRequeridos = new ArrayList<WebElement>(); // Lista de los campos NO requeridos en Solicitud

        // Separar campos requeridos de los no requeridos
        for (int i = 0; i< listaDatosSolicitud.size(); i++){
            if(listaDatosSolicitud.get(i).getText().contains("*")){
                listaDatosSolicitudRequeridos.add(listaDatosSolicitud.get(i));
            }
            /*
            if(!listaDatosSolicitud.get(i).getText().contains("*")){
                listaDatosSolicitudNoRequeridos.add(listaDatosSolicitud.get(i));
            }*/
        }

        // Lista de los campos en Datos Generales
        List<WebElement> listaDatosGenerales = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[2]/div"));

        List<WebElement> listaDatosGeneralesRequeridos = new ArrayList<WebElement>(); // Lista de los campos requeridos en Datos Generales
        List<WebElement> listaDatosGeneralesNoRequeridos = new ArrayList<WebElement>(); // Lista de los campos NO requeridos en Datos Generales

        // Separar campos requeridos de los no requeridos
        for (int i = 0; i< listaDatosGenerales.size(); i++){
            if(listaDatosGenerales.get(i).getText().contains("*")){
                listaDatosGeneralesRequeridos.add(listaDatosGenerales.get(i));
            }
            /*
            if(!listaDatosGenerales.get(i).getText().contains("*")){
                listaDatosGeneralesNoRequeridos.add(listaDatosGenerales.get(i));
            }*/
        }

        // Lista de los campos en Domicilio Actual
        List<WebElement> listaDomicilioActual = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[3]/div"));

        List<WebElement> listaDomicilioActualRequeridos = new ArrayList<WebElement>(); // Lista de los campos requeridos en Domicilio Actual
        List<WebElement> listaDomicilioActualNoRequeridos = new ArrayList<WebElement>(); // Lista de los campos NO requeridos en Domicilio Actual

        // Separar campos requeridos de los no requeridos
        for (int i = 0; i< listaDomicilioActual.size(); i++){
            if(listaDomicilioActual.get(i).getText().contains("*")){
                listaDomicilioActualRequeridos.add(listaDomicilioActual.get(i));
            }
            /*
            if(!listaDomicilioActual.get(i).getText().contains("*")){
                listaDomicilioActualNoRequeridos.add(listaDomicilioActual.get(i));
            }*/
        }

        // Lista de los campos en Medios de Contacto
        List<WebElement> listaMediosContacto = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[4]/div"));

        List<WebElement> listaMediosContactoRequeridos = new ArrayList<WebElement>(); // Lista de los campos requeridos en Medios de Contacto
        List<WebElement> listaMediosContactoNoRequeridos = new ArrayList<WebElement>(); // Lista de los campos NO requeridos en Medios de Contacto

        // Separar campos requeridos de los no requeridos
        for (int i = 0; i< listaMediosContacto.size(); i++){
            if(listaMediosContacto.get(i).getText().contains("*")){
                listaMediosContactoRequeridos.add(listaMediosContacto.get(i));
            }
            /*
            if(!listaMediosContacto.get(i).getText().contains("*")){
                listaMediosContactoNoRequeridos.add(listaMediosContacto.get(i));
            }*/
        }

        // Lista de los campos en Nivel Educativo

        // ==== Area Izquierda ====
        List<WebElement> listaAreaIzquierdaNE = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[5]/div[2]/div/div"));

        List<WebElement> listaAreaIzquierdaNERequeridos = new ArrayList<WebElement>(); // Lista de los campos requeridos en Nivel Educativo Area Izquierda
        List<WebElement> listaAreaIzquierdaNENoRequeridos = new ArrayList<WebElement>(); // Lista de los campos NO requeridos en Nivel Educativo Area Izquierda

        // Separar campos requeridos de los no requeridos
        for (int i = 0; i< listaAreaIzquierdaNE.size(); i++){
            if(listaAreaIzquierdaNE.get(i).getText().contains("*")){
                listaAreaIzquierdaNERequeridos.add(listaAreaIzquierdaNE.get(i));
            }
            /*
            if(!listaAreaIzquierdaNE.get(i).getText().contains("*")){
                listaAreaIzquierdaNENoRequeridos.add(listaAreaIzquierdaNE.get(i));
            }*/
        }

        // ==== Area Derecha ====
        List<WebElement> listaAreaDerechaNE = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[5]/div[3]/div/div"));

        List<WebElement> listaAreaDerechaNERequeridos = new ArrayList<WebElement>(); // Lista de los campos requeridos en Nivel Educativo Area Derecha
        List<WebElement> listaAreaDerechaNENoRequeridos = new ArrayList<WebElement>(); // Lista de los campos NO requeridos en Nivel Educativo Area Derecha

        // Separar campos requeridos de los no requeridos
        for (int i = 0; i< listaAreaDerechaNE.size(); i++){
            if(listaAreaDerechaNE.get(i).getText().contains("*")){
                listaAreaDerechaNERequeridos.add(listaAreaDerechaNE.get(i));
            }
            /*
            if(!listaAreaDerechaNE.get(i).getText().contains("*")){
                listaAreaDerechaNENoRequeridos.add(listaAreaDerechaNE.get(i));
            }*/
        }

        // Lista de los campos en Contacto de Emergencia
        List<WebElement> listaContactoEmergencia = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[6]/div"));

        List<WebElement> listaContactoEmergenciaRequeridos = new ArrayList<WebElement>(); // Lista de los campos requeridos en Contacto de Emergencia
        List<WebElement> listaContactoEmergenciaNoRequeridos = new ArrayList<WebElement>(); // Lista de los campos NO requeridos en Contacto de Emergencia

        // Separar campos requeridos de los no requeridos
        for (int i = 0; i< listaContactoEmergencia.size(); i++){
            if(listaContactoEmergencia.get(i).getText().contains("*")){
                listaContactoEmergenciaRequeridos.add(listaContactoEmergencia.get(i));
            }
            /*
            if(!listaContactoEmergencia.get(i).getText().contains("*")){
                listaContactoEmergenciaNoRequeridos.add(listaContactoEmergencia.get(i));
            }*/
        }

        // Lista de los campos en Información Financiera

        // ==== Area Izquierda ====
        List<WebElement> listaAreaIzquierdaIF = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-2\"]/div/div/app-add-admision/div[2]/div/div/div[7]/div[2]/div"));

        List<WebElement> listaAreaIzquierdaIFRequeridos = new ArrayList<WebElement>(); // Lista de los campos requeridos en Información Financiera Area Izquierda
        List<WebElement> listaAreaIzquierdaIFNoRequeridos = new ArrayList<WebElement>(); // Lista de los campos NO requeridos en Información Financiera Area Izquierda

        // Separar campos requeridos de los no requeridos
        for (int i = 0; i< listaAreaIzquierdaIF.size(); i++){
            if(listaAreaIzquierdaIF.get(i).getText().contains("*")){
                listaAreaIzquierdaIFRequeridos.add(listaAreaIzquierdaIF.get(i));
            }
            /*
            if(!listaAreaIzquierdaIF.get(i).getText().contains("*")){
                listaAreaIzquierdaIFNoRequeridos.add(listaAreaIzquierdaIF.get(i));
            }*/
        }

        // ==== Area Derecha ====
        List<WebElement> listaAreaDerechaIF = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-2\"]/div/div/app-add-admision/div[2]/div/div/div[7]/div[3]/div"));

        List<WebElement> listaAreaDerechaIFRequeridos = new ArrayList<WebElement>(); // Lista de los campos requeridos en Información Financiera Area Derecha
        List<WebElement> listaAreaDerechaIFNoRequeridos = new ArrayList<WebElement>(); // Lista de los campos NO requeridos en Información Financiera Area Derecha

        // Separar campos requeridos de los no requeridos
        for (int i = 0; i< listaAreaDerechaIF.size(); i++){
            if(listaAreaDerechaIF.get(i).getText().contains("*")){
                listaAreaDerechaIFRequeridos.add(listaAreaDerechaIF.get(i));
            }
            /*
            if(!listaAreaDerechaIF.get(i).getText().contains("*")){
                listaAreaDerechaIFNoRequeridos.add(listaAreaDerechaIF.get(i));
            }*/
        }

        // Lista de los campos en Datos Médicos
        List<WebElement> listaDatosMedicos = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-2\"]/div/div/app-add-admision/div[2]/div/div/div[8]/div"));

        List<WebElement> listaDatosMedicosRequeridos = new ArrayList<WebElement>(); // Lista de los campos requeridos en Datos Médicos
        List<WebElement> listaDatosMedicosNoRequeridos = new ArrayList<WebElement>(); // Lista de los campos NO requeridos en Datos Médicos

        // Separar campos requeridos de los no requeridos
        for (int i = 0; i< listaDatosMedicos.size(); i++){
            if(listaDatosMedicos.get(i).getText().contains("*")){
                listaDatosMedicosRequeridos.add(listaDatosMedicos.get(i));
            }
            /*
            if(!listaDatosMedicos.get(i).getText().contains("*")){
                listaDatosMedicosNoRequeridos.add(listaDatosMedicos.get(i));
            }*/
        }

        // Lista de los campos en Dificultades
        List<WebElement> listaDificultades = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-2\"]/div/div/app-add-admision/div[2]/div/div/div[9]/div[2]/div"));

        List<WebElement> listaDificultadesRequeridos = new ArrayList<WebElement>(); // Lista de los campos requeridos en Dificultades
        List<WebElement> listaDificultadesNoRequeridos = new ArrayList<WebElement>(); // Lista de los campos NO requeridos en Dificultades

        // Separar campos requeridos de los no requeridos
        for (int i = 0; i< listaDificultades.size(); i++){
            if(listaDificultades.get(i).getText().contains("*")){
                listaDificultadesRequeridos.add(listaDificultades.get(i));
            }
            /*
            if(!listaDificultades.get(i).getText().contains("*")){
                listaDificultadesNoRequeridos.add(listaDificultades.get(i));
            }*/
        }

        // Lista de los campos en Información Adicional
        List<WebElement> listaInformacionAdicional = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-2\"]/div/div/app-add-admision/div[2]/div/div/div[10]/div"));

        List<WebElement> listaInformacionAdicionalRequeridos = new ArrayList<WebElement>(); // Lista de los campos requeridos en Información Adicional
        List<WebElement> listaInformacionAdicionalNoRequeridos = new ArrayList<WebElement>(); // Lista de los campos NO requeridos en Información Adicional

        // Separar campos requeridos de los no requeridos
        for (int i = 0; i< listaInformacionAdicional.size(); i++){
            if(listaInformacionAdicional.get(i).getText().contains("*")){
                listaInformacionAdicionalRequeridos.add(listaInformacionAdicional.get(i));
            }
            /*
            if(!listaInformacionAdicional.get(i).getText().contains("*")){
                listaInformacionAdicionalNoRequeridos.add(listaInformacionAdicional.get(i));
            }*/
        }

        String mjCampoRequerido = "Este campo es requerido";
        // Asserciones

        /// Solicitud
        for (WebElement item : listaDatosSolicitudRequeridos){
            assertTrue(item.getText().contains(mjCampoRequerido));
        }

        /// Datos Generales
        for (WebElement item : listaDatosGeneralesRequeridos){
            if (item.getText().contains(mjCampoRequerido)){
                assertTrue(item.getText().contains(mjCampoRequerido));
            }
        }

        /// Domicilio Actual
        for (WebElement item : listaDomicilioActualRequeridos){
            assertTrue(item.getText().contains(mjCampoRequerido));
        }

        /// Medios de Contacto
        for (WebElement item : listaMediosContactoRequeridos){
            assertTrue(item.getText().contains(mjCampoRequerido));
        }

        /// Nivel Educativo Izquierda
        for (WebElement item : listaAreaIzquierdaNERequeridos){
            assertTrue(item.getText().contains(mjCampoRequerido));
        }

        /// Nivel Educativo Derecha
        for (WebElement item : listaAreaDerechaNERequeridos){
            assertTrue(item.getText().contains(mjCampoRequerido));
        }

        /// Contacto de Emergencia
        for (WebElement item : listaContactoEmergenciaRequeridos){
            assertTrue(item.getText().contains(mjCampoRequerido));
        }

        /// Información Financiera Izquierda
        for (WebElement item : listaAreaIzquierdaIFRequeridos){
            assertTrue(item.getText().contains(mjCampoRequerido));
        }

        /// Información Financiera Derecha
        for (WebElement item : listaAreaDerechaIFRequeridos){
            assertTrue(item.getText().contains(mjCampoRequerido));
        }

        /// Datos Médicos
        for (WebElement item : listaDatosMedicosRequeridos){
            assertTrue(item.getText().contains(mjCampoRequerido));
        }

        /// Dificultades
        for (WebElement item : listaDificultadesRequeridos){
            assertTrue(item.getText().contains(mjCampoRequerido));
        }

        /// Información Adicional
        for (WebElement item : listaInformacionAdicionalRequeridos){
            assertTrue(item.getText().contains(mjCampoRequerido));
        }

    }

    // Validar – Mensaje campos requeridos
    @Test
    public void SolicitudMensajeCamposRequeridos() {

        // Introducir email (Usuario)
        WebElement usuarioInput = driver.findElement(By.id("email"));
        usuarioInput.sendKeys("bussoifoiteimmou-2816@yopmail.com");

        // Introducir contraseña
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("lAS14789@");

        // Dar click al boton Iniciar sesión
        WebElement btnLogin = driver.findElement(By.id("btnLogin"));
        btnLogin.click();

        try {
            Thread.sleep(4000);
        } catch (Exception e) {

        }

        // Hacer clic al boton "ADMISIÓN TECNOLOGO"
        WebElement btnAdmision = driver.findElement(By.xpath("//*[@id=\"kt_content\"]/div/div/div/div/div/div[2]/div[2]/div/div[1]/div[1]/a/div/div[1]/div[2]/div[3]"));
        btnAdmision.click();

        // Hacer clic al boton "Realizar Solicitud"
        WebElement solicitud = driver.findElement(By.id("btnAdd"));
        solicitud.click();

        // Crear tiempo
        tiempo(5000);

        // Hacer clic en el boton "Guardar"
        WebElement guardar = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[3]/button[2]/span"));
        guardar.click();

        // Ventana modal
        WebElement vModal = driver.findElement(By.xpath("//*[@id=\"swal2-html-container\"]"));

        // Assertion
        assertEquals(
                "Favor completar correctamente los campos requeridos",
                vModal.getText(),
                "El mensaje deberia ser: Favor completar correctamente los campos requeridos"
        );
    }

    // Validar mensaje - Proceso de solicitud de admisión concluido
    @Test
    public void ProcesoSolicitudConcluido(){

        // Introducir email (Usuario)
        WebElement usuarioInput = driver.findElement(By.id("email"));
        usuarioInput.sendKeys("bussoifoiteimmou-2816@yopmail.com");

        // Introducir contraseña
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("lAS14789@");

        // Dar click al boton Iniciar sesión
        WebElement btnLogin = driver.findElement(By.id("btnLogin"));
        btnLogin.click();

        tiempo(4000);

        // Hacer clic al boton "ADMISIÓN TECNOLOGO"
        WebElement btnAdmision = driver.findElement(By.xpath("//*[@id=\"kt_content\"]/div/div/div/div/div/div[2]/div[2]/div/div[1]/div[1]/a/div/div[1]"));
        btnAdmision.click();

        tiempo(4000);

        // Hacer clic al boton "Realizar Solicitud"
        WebElement solicitud= driver.findElement(By.xpath("//*[@id=\"btnAdd\"]"));
        solicitud.click();

        // Obtener mensaje de advertencia dobre el tiempo para el porceso de admicion culminado
        WebElement msjProcesoConcluido = driver.findElement(By.xpath("//*[@id=\"swal2-title\"]"));

        // Validar que el mensaje que aparezca sea el esperado
        assertEquals(
                "Estimado usuario: El proceso de solicitud de admisión para este período ha concluido, favor estar atento al calendario de admisiones para el próximo período.\n" +
                        "\n" +
                        "\n" +
                        "Att. Departamento de admisión.",
                msjProcesoConcluido.getText(),
                "El mensaje deberia ser: Estimado usuario: El proceso de solicitud de admisión para este período ha concluido, favor estar atento al calendario de admisiones para el próximo período.\n" +
                        "\n" +
                        "\n" +
                        "Att. Departamento de admisión."
        );
    }

    // Crear tiempo
    public void tiempo(int milisegundos){
        try {
            Thread.sleep(milisegundos);
        }catch (Exception e){

        }
    }
}
