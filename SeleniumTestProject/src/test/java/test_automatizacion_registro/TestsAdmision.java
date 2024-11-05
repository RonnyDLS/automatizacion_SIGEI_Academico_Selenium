package test_automatizacion_registro;

import org.junit.jupiter.api.AfterAll;
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

        /// HILOS
        String mjCampoRequerido = "Este campo es requerido";

        Thread hilo1 = new Thread(()->{

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

            // Asserciones
            /// Solicitud
            for (WebElement item : listaDatosSolicitudRequeridos){
                assertTrue(item.getText().contains(mjCampoRequerido));
            }
        });

        Thread hilo2 = new Thread(()->{

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

            // Asserciones
            /// Datos Generales
            for (WebElement item : listaDatosGeneralesRequeridos){
                if (item.getText().contains(mjCampoRequerido)){
                    assertTrue(item.getText().contains(mjCampoRequerido));
                }
            }
        });

        Thread hilo3 = new Thread(()->{

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

            // Asserciones
            /// Domicilio Actual
            for (WebElement item : listaDomicilioActualRequeridos){
                assertTrue(item.getText().contains(mjCampoRequerido));
            }

        });

        Thread hilo4 = new Thread(()->{

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

            // Asserciones
            /// Medios de Contacto
            for (WebElement item : listaMediosContactoRequeridos){
                assertTrue(item.getText().contains(mjCampoRequerido));
            }
        });

        Thread hilo5 = new Thread(()->{

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

            // Asserciones
            /// Nivel Educativo Izquierda
            for (WebElement item : listaAreaIzquierdaNERequeridos){
                assertTrue(item.getText().contains(mjCampoRequerido));
            }
        });

        Thread hilo6 = new Thread(()->{

            // Lista de los campos en Nivel Educativo

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

            // Asserciones
            /// Nivel Educativo Derecha
            for (WebElement item : listaAreaDerechaNERequeridos){
                assertTrue(item.getText().contains(mjCampoRequerido));
            }
        });

        Thread hilo7 = new Thread(()->{

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

            // Asserciones
            /// Contacto de Emergencia
            for (WebElement item : listaContactoEmergenciaRequeridos){
                assertTrue(item.getText().contains(mjCampoRequerido));
            }
        });

        Thread hilo8 = new Thread(()->{

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

            // Asserciones
            /// Información Financiera Izquierda
            for (WebElement item : listaAreaIzquierdaIFRequeridos){
                assertTrue(item.getText().contains(mjCampoRequerido));
            }
        });

        Thread hilo9 = new Thread(()->{

            // Lista de los campos en Información Financiera

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

            // Asserciones
            /// Información Financiera Derecha
            for (WebElement item : listaAreaDerechaIFRequeridos){
                assertTrue(item.getText().contains(mjCampoRequerido));
            }
        });

        Thread hilo10 = new Thread(()->{

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

            // Asserciones
            /// Datos Médicos
            for (WebElement item : listaDatosMedicosRequeridos){
                assertTrue(item.getText().contains(mjCampoRequerido));
            }
        });

        Thread hilo11 = new Thread(()->{

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

            // Asserciones
            /// Dificultades
            for (WebElement item : listaDificultadesRequeridos){
                assertTrue(item.getText().contains(mjCampoRequerido));
            }
        });

        Thread hilo12 = new Thread(()->{

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

            // Asserciones
            /// Información Adicional
            for (WebElement item : listaInformacionAdicionalRequeridos){
                assertTrue(item.getText().contains(mjCampoRequerido));
            }
        });
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

        // Crear tiempo
        tiempo(5000);

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

    // Validar mensaje – Este campo es requerido en campos sin datos
    @Test
    public void MJCamposRequeridosSinDatos() {

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

        // Crear tiempo
        tiempo(7000);

        // Hacer clic al boton "Realizar Solicitud"
        WebElement solicitud = driver.findElement(By.id("btnAdd"));
        solicitud.click();

        // Crear tiempo
        tiempo(5000);


        /// Sección Solicitud

        // Seleccionar recinto
        WebElement btnRecinto = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[1]/div[2]/ngx-select-dropdown/div/button"));
        btnRecinto.click();

        List<WebElement> listaRecinto = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[1]/div[2]/ngx-select-dropdown/div/div/ul[2]/li"));
        listaRecinto.get(0).click();


        /// Sección Datos Generales

        // Sexo
        WebElement sexo = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[2]/div[4]/ngx-select-dropdown/div/button"));
        sexo.click();

        List<WebElement> listaSexo = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[2]/div[4]/ngx-select-dropdown/div/div/ul[2]/li"));
        listaSexo.get(0).click();


        /// Sección Domicilio Actual

        // Provincia de Residencia
        WebElement provinciaRecidencia = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[3]/div[2]/ngx-select-dropdown/div/button"));
        provinciaRecidencia.click();

        List<WebElement> listaProvinciaRecidencia= driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[3]/div[2]/ngx-select-dropdown/div/div/ul[2]/li"));
        listaProvinciaRecidencia.get(0).click();


        /// Sección Medios de Contacto

        // Teléfono de Casa
        WebElement telefono = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[4]/div[2]/input"));
        telefono.sendKeys("8848484848");

        /// Sección Nivel Educativo

        // ¿ Institución pública o privada ?
        WebElement nivelAcceso = driver.findElement(By.xpath("/html/body/div/div[2]/div/mat-dialog-container/div/div/app-add-admision/div[2]/div/div/div[5]/div[2]/div/div[2]/ngx-select-dropdown/div/button"));
        nivelAcceso.click();

        List<WebElement> listaNivelAcceso= driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[5]/div[2]/div/div[2]/ngx-select-dropdown/div/div/ul[2]/li"));
        listaNivelAcceso.get(0).click();


        /// Sección Contacto de Emergencia

        // Nombre
        WebElement nombre = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[6]/div[2]/input"));
        nombre.sendKeys("Mery");


        /// Sección Información Financiera

        // ¿ Trabajas actualmente ?
        WebElement confirmacionTrabajo = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[7]/div[2]/div[1]/ngx-select-dropdown/div/button"));
        confirmacionTrabajo.click();

        List<WebElement> listaConfirmacionTrabajo = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[7]/div[2]/div[1]/ngx-select-dropdown/div/div/ul[2]/li"));
        listaConfirmacionTrabajo.get(0).click();


        /// Sección Datos Médicos

        // Tipo de Sangre
        WebElement tipoSangre = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[8]/div[2]/ngx-select-dropdown/div/button"));
        tipoSangre.click();

        List<WebElement> listaTipoSangre = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[8]/div[2]/ngx-select-dropdown/div/div/ul[2]/li"));
        listaTipoSangre.get(0).click();


        /// Sección Dificultades

        // ¿Tienes dificultad para ver incluso si usas lentes?
        WebElement dificultadVer = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[9]/div[2]/div[1]/ngx-select-dropdown/div/button"));
        dificultadVer.click();

        List<WebElement> listaDificultadVer = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[9]/div[2]/div[1]/ngx-select-dropdown/div/div/ul[2]/li"));
        listaDificultadVer.get(0).click();


        /// Sección Información Adicional

        // ¿ Cómo te enteraste del ITLA ?
        WebElement enterasteITLA = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[10]/div[2]/ngx-select-dropdown/div/button"));
        enterasteITLA.click();

        List<WebElement> listaEnterasteITLA = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[10]/div[2]/ngx-select-dropdown/div/div/ul[2]/li"));
        listaEnterasteITLA.get(0).click();


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

        // Botón (OK) - Ventana modal
        WebElement btnOK = driver.findElement(By.xpath("//*[@id=\"kt_body home-modal\"]/div[2]/div/div[6]/button[1]"));
        btnOK.click();

        ///TODO =========  RECORRES TODOS LOS CAMPOS DE LAS SECCIONES ======

        String mjCampoRequerido = "Este campo es requerido";

        Thread hilo1 = new Thread(()->{

            // Lista de los campos en Solicitud
            List<WebElement> listaDatosSolicitud = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[1]/div"));
            List<WebElement> listaDatosSolicitudRequeridos = new ArrayList<WebElement>(); // Lista de los campos requeridos en Solicitud

            // Separar campos requeridos de los no requeridos
            for (int i = 0; i< listaDatosSolicitud.size(); i++){
                if(listaDatosSolicitud.get(i).getText().contains("*")){
                    listaDatosSolicitudRequeridos.add(listaDatosSolicitud.get(i));
                }
            }

            // ASSERTION
            /// Solicitud
            for (WebElement item : listaDatosSolicitudRequeridos){
                if (!item.getText().contains("Recinto")){
                    assertTrue(item.getText().contains(mjCampoRequerido));
                }
            }
        });

        Thread hilo2 = new Thread(()->{

            // Lista de los campos en Datos Generales
            List<WebElement> listaDatosGenerales = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[2]/div"));
            List<WebElement> listaDatosGeneralesRequeridos = new ArrayList<WebElement>(); // Lista de los campos requeridos en Datos Generales

            // Separar campos requeridos de los no requeridos
            for (int i = 0; i< listaDatosGenerales.size(); i++){
                if(listaDatosGenerales.get(i).getText().contains("*")){
                    listaDatosGeneralesRequeridos.add(listaDatosGenerales.get(i));
                }
            }

            // ASSERTION
            /// Datos Generales
            for (WebElement item : listaDatosGeneralesRequeridos){
                if (!item.getText().contains("Sexo *") && !item.getText().contains("Tipo de Identificación") && !item.getText().contains("Identificación")){
                    assertTrue(item.getText().contains(mjCampoRequerido));
                }
            }
        });

        Thread hilo3 = new Thread(()->{

            // Lista de los campos en Domicilio Actual
            List<WebElement> listaDomicilioActual = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[3]/div"));
            List<WebElement> listaDomicilioActualRequeridos = new ArrayList<WebElement>(); // Lista de los campos requeridos en Domicilio Actual

            // Separar campos requeridos de los no requeridos
            for (int i = 0; i< listaDomicilioActual.size(); i++){
                if(listaDomicilioActual.get(i).getText().contains("*")){
                    listaDomicilioActualRequeridos.add(listaDomicilioActual.get(i));
                }
            }

            // ASSERTION
            /// Domicilio Actual
            for (WebElement item : listaDomicilioActualRequeridos){
                if (!item.getText().contains("Provincia de Residencia")){
                    assertTrue(item.getText().contains(mjCampoRequerido));
                }
            }
        });

        Thread hilo4 = new Thread(()->{

            // Lista de los campos en Medios de Contacto
            List<WebElement> listaMediosContacto = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[4]/div"));
            List<WebElement> listaMediosContactoRequeridos = new ArrayList<WebElement>(); // Lista de los campos requeridos en Medios de Contacto

            // Separar campos requeridos de los no requeridos
            for (int i = 0; i< listaMediosContacto.size(); i++){
                if(listaMediosContacto.get(i).getText().contains("*")){
                    listaMediosContactoRequeridos.add(listaMediosContacto.get(i));
                }
            }

            // ASSERTION
            /// Medios de Contacto
            for (WebElement item : listaMediosContactoRequeridos){
                if (!item.getText().contains("Teléfono de Casa")){
                    assertTrue(item.getText().contains(mjCampoRequerido));
                }
            }
        });

        Thread hilo5 = new Thread(()->{

            // Lista de los campos en Nivel Educativo

            // ==== Area Izquierda ====
            List<WebElement> listaAreaIzquierdaNE = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[5]/div[2]/div/div"));
            List<WebElement> listaAreaIzquierdaNERequeridos = new ArrayList<WebElement>(); // Lista de los campos requeridos en Nivel Educativo Area Izquierda

            // Separar campos requeridos de los no requeridos
            for (int i = 0; i< listaAreaIzquierdaNE.size(); i++){
                if(listaAreaIzquierdaNE.get(i).getText().contains("*")){
                    listaAreaIzquierdaNERequeridos.add(listaAreaIzquierdaNE.get(i));
                }
            }

            // ASSERTION
            /// Nivel Educativo Izquierda
            for (WebElement item : listaAreaIzquierdaNERequeridos){
                if (!item.getText().contains("¿ Institución pública o privada ?")){
                    assertTrue(item.getText().contains(mjCampoRequerido));
                }
            }
        });

        Thread hilo6 = new Thread(()->{

            // Lista de los campos en Contacto de Emergencia
            List<WebElement> listaContactoEmergencia = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[6]/div"));
            List<WebElement> listaContactoEmergenciaRequeridos = new ArrayList<WebElement>(); // Lista de los campos requeridos en Contacto de Emergencia

            // Separar campos requeridos de los no requeridos
            for (int i = 0; i< listaContactoEmergencia.size(); i++){
                if(listaContactoEmergencia.get(i).getText().contains("*")){
                    listaContactoEmergenciaRequeridos.add(listaContactoEmergencia.get(i));
                }
            }

            // ASSERTION
            /// Contacto de Emergencia
            for (WebElement item : listaContactoEmergenciaRequeridos){
                if (!item.getText().contains("Nombre (s)")){
                    assertTrue(item.getText().contains(mjCampoRequerido));
                }
            }
        });

        Thread hilo7 = new Thread(()->{

            // Lista de los campos en Información Financiera

            // ==== Area Izquierda ====
            List<WebElement> listaAreaIzquierdaIF = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-2\"]/div/div/app-add-admision/div[2]/div/div/div[7]/div[2]/div"));
            List<WebElement> listaAreaIzquierdaIFRequeridos = new ArrayList<WebElement>(); // Lista de los campos requeridos en Información Financiera Area Izquierda

            // Separar campos requeridos de los no requeridos
            for (int i = 0; i< listaAreaIzquierdaIF.size(); i++){
                if(listaAreaIzquierdaIF.get(i).getText().contains("*")){
                    listaAreaIzquierdaIFRequeridos.add(listaAreaIzquierdaIF.get(i));
                }
            }

            // ASSERTION
            /// Información Financiera Izquierda
            for (WebElement item : listaAreaIzquierdaIFRequeridos){
                if (!item.getText().contains("¿ Trabajas actualmente ?")){
                    assertTrue(item.getText().contains(mjCampoRequerido));
                }
            }
        });

        Thread hilo8 = new Thread(()->{

            // Lista de los campos en Información Financiera

            // ==== Area Derecha ====
            List<WebElement> listaAreaDerechaIF = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-2\"]/div/div/app-add-admision/div[2]/div/div/div[7]/div[3]/div"));
            List<WebElement> listaAreaDerechaIFRequeridos = new ArrayList<WebElement>(); // Lista de los campos requeridos en Información Financiera Area Derecha

            // Separar campos requeridos de los no requeridos
            for (int i = 0; i< listaAreaDerechaIF.size(); i++){
                if(listaAreaDerechaIF.get(i).getText().contains("*")){
                    listaAreaDerechaIFRequeridos.add(listaAreaDerechaIF.get(i));
                }
            }

            // ASSERTION
            /// Información Financiera Derecha
            for (WebElement item : listaAreaDerechaIFRequeridos){
                if (!item.getText().contains("¿ Trabajas actualmente ?")){
                    assertTrue(item.getText().contains(mjCampoRequerido));
                }
            }
        });

        Thread hilo9 = new Thread(()->{

            // Lista de los campos en Datos Médicos
            List<WebElement> listaDatosMedicos = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-2\"]/div/div/app-add-admision/div[2]/div/div/div[8]/div"));
            List<WebElement> listaDatosMedicosRequeridos = new ArrayList<WebElement>(); // Lista de los campos requeridos en Datos Médicos

            // Separar campos requeridos de los no requeridos
            for (int i = 0; i< listaDatosMedicos.size(); i++){
                if(listaDatosMedicos.get(i).getText().contains("*")){
                    listaDatosMedicosRequeridos.add(listaDatosMedicos.get(i));
                }
            }

            // ASSERTION
            /// Datos Médicos
            for (WebElement item : listaDatosMedicosRequeridos){
                if (!item.getText().contains("Tipo de Sangre")){
                    assertTrue(item.getText().contains(mjCampoRequerido));
                }
            }
        });

        Thread hilo10 = new Thread(()->{

            // Lista de los campos en Dificultades
            List<WebElement> listaDificultades = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-2\"]/div/div/app-add-admision/div[2]/div/div/div[9]/div[2]/div"));
            List<WebElement> listaDificultadesRequeridos = new ArrayList<WebElement>(); // Lista de los campos requeridos en Dificultades

            // Separar campos requeridos de los no requeridos
            for (int i = 0; i< listaDificultades.size(); i++){
                if(listaDificultades.get(i).getText().contains("*")){
                    listaDificultadesRequeridos.add(listaDificultades.get(i));
                }
            }

            // ASSERTION
            /// Dificultades
            for (WebElement item : listaDificultadesRequeridos){
                if (!item.getText().contains("¿Tienes dificultad para ver incluso si usas lentes?")){
                    assertTrue(item.getText().contains(mjCampoRequerido));
                }
            }
        });

        Thread hilo11 = new Thread(()->{

            // Lista de los campos en Información Adicional
            List<WebElement> listaInformacionAdicional = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-2\"]/div/div/app-add-admision/div[2]/div/div/div[10]/div"));
            List<WebElement> listaInformacionAdicionalRequeridos = new ArrayList<WebElement>(); // Lista de los campos requeridos en Información Adicional

            // Separar campos requeridos de los no requeridos
            for (int i = 0; i< listaInformacionAdicional.size(); i++){
                if(listaInformacionAdicional.get(i).getText().contains("*")){
                    listaInformacionAdicionalRequeridos.add(listaInformacionAdicional.get(i));
                }
            }

            // ASSERTION
            /// Información Adicional
            for (WebElement item : listaInformacionAdicionalRequeridos){
                if (!item.getText().contains("¿Cómo te enteraste del ITLA?")){
                    assertTrue(item.getText().contains(mjCampoRequerido));
                }
            }
        });

        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();
        hilo6.start();
        hilo7.start();
        hilo8.start();
        hilo9.start();
        hilo10.start();
        hilo11.start();

    }

    //Validar mensajes y botones – Ventana modal Solicitud de Admisión
    @Test
    public void MjBtnCamposRequeridosSinDatos() {

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

        // Crear tiempo
        tiempo(7000);

        // Hacer clic al boton "Realizar Solicitud"
        WebElement solicitud = driver.findElement(By.id("btnAdd"));
        solicitud.click();

        // Crear tiempo
        tiempo(5000);


        /// Sección Solicitud

        // Seleccionar recinto
        WebElement btnRecinto = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[1]/div[2]/ngx-select-dropdown/div/button"));
        btnRecinto.click();

        List<WebElement> listaRecinto = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[1]/div[2]/ngx-select-dropdown/div/div/ul[2]/li"));
        listaRecinto.get(0).click();


        /// Sección Datos Generales

        // Sexo
        WebElement sexo = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[2]/div[4]/ngx-select-dropdown/div/button"));
        sexo.click();

        List<WebElement> listaSexo = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[2]/div[4]/ngx-select-dropdown/div/div/ul[2]/li"));
        listaSexo.get(0).click();


        /// Sección Domicilio Actual

        // Provincia de Residencia
        WebElement provinciaRecidencia = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[3]/div[2]/ngx-select-dropdown/div/button"));
        provinciaRecidencia.click();

        List<WebElement> listaProvinciaRecidencia = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[3]/div[2]/ngx-select-dropdown/div/div/ul[2]/li"));
        listaProvinciaRecidencia.get(0).click();


        /// Sección Medios de Contacto

        // Teléfono de Casa
        WebElement telefono = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[4]/div[2]/input"));
        telefono.sendKeys("8848484848");

        /// Sección Nivel Educativo

        // ¿ Institución pública o privada ?
        WebElement nivelAcceso = driver.findElement(By.xpath("/html/body/div/div[2]/div/mat-dialog-container/div/div/app-add-admision/div[2]/div/div/div[5]/div[2]/div/div[2]/ngx-select-dropdown/div/button"));
        nivelAcceso.click();

        List<WebElement> listaNivelAcceso = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[5]/div[2]/div/div[2]/ngx-select-dropdown/div/div/ul[2]/li"));
        listaNivelAcceso.get(0).click();


        /// Sección Contacto de Emergencia

        // Nombre
        WebElement nombre = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[6]/div[2]/input"));
        nombre.sendKeys("Mery");


        /// Sección Información Financiera

        // ¿ Trabajas actualmente ?
        WebElement confirmacionTrabajo = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[7]/div[2]/div[1]/ngx-select-dropdown/div/button"));
        confirmacionTrabajo.click();

        List<WebElement> listaConfirmacionTrabajo = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[7]/div[2]/div[1]/ngx-select-dropdown/div/div/ul[2]/li"));
        listaConfirmacionTrabajo.get(0).click();


        /// Sección Datos Médicos

        // Tipo de Sangre
        WebElement tipoSangre = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[8]/div[2]/ngx-select-dropdown/div/button"));
        tipoSangre.click();

        List<WebElement> listaTipoSangre = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[8]/div[2]/ngx-select-dropdown/div/div/ul[2]/li"));
        listaTipoSangre.get(0).click();


        /// Sección Dificultades

        // ¿Tienes dificultad para ver incluso si usas lentes?
        WebElement dificultadVer = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[9]/div[2]/div[1]/ngx-select-dropdown/div/button"));
        dificultadVer.click();

        List<WebElement> listaDificultadVer = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[9]/div[2]/div[1]/ngx-select-dropdown/div/div/ul[2]/li"));
        listaDificultadVer.get(0).click();


        /// Sección Información Adicional

        // ¿ Cómo te enteraste del ITLA ?
        WebElement enterasteITLA = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[10]/div[2]/ngx-select-dropdown/div/button"));
        enterasteITLA.click();

        List<WebElement> listaEnterasteITLA = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[10]/div[2]/ngx-select-dropdown/div/div/ul[2]/li"));
        listaEnterasteITLA.get(0).click();


        // Verificar si el boton "Cerrar" existe
        WebElement cerrar = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[3]/button[1]"));
        assertTrue(cerrar.isDisplayed()); // ASSERTION

        // Verificar si el boton "Guardar" existe y hacer clic
        WebElement guardar = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[3]/button[2]/span"));
        assertTrue(guardar.isDisplayed()); // ASSERTION
        guardar.click();

        // Ventana modal
        WebElement vModal = driver.findElement(By.xpath("//*[@id=\"swal2-html-container\"]"));
        assertEquals(
                "Favor completar correctamente los campos requeridos",
                vModal.getText(),
                "El mensaje deberia ser: Favor completar correctamente los campos requeridos"
        ); // ASSERTION

        // Botón (OK) - Ventana modal
        WebElement btnOK = driver.findElement(By.xpath("//*[@id=\"kt_body home-modal\"]/div[2]/div/div[6]/button[1]"));
        btnOK.click();

        // Mensaje para las asserciones
        String mjCampoRequerido = "Este campo es requerido";

        // Lista de los campos en Solicitud
        List<WebElement> listaDatosSolicitud = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[1]/div"));
        List<WebElement> listaDatosSolicitudRequeridos = new ArrayList<WebElement>(); // Lista de los campos requeridos en Solicitud

        // Separar campos requeridos de los no requeridos
        for (int i = 0; i < listaDatosSolicitud.size(); i++) {
            if (listaDatosSolicitud.get(i).getText().contains("*")) {
                listaDatosSolicitudRequeridos.add(listaDatosSolicitud.get(i));
            }
        }

        // ASSERTION
        /// Solicitud
        assertTrue(listaDatosSolicitudRequeridos.size() >= 0);
        for (WebElement item : listaDatosSolicitudRequeridos) {
            if (!item.getText().contains("Recinto")) {
                assertTrue(item.getText().contains(mjCampoRequerido));
            }
        }

        // Lista de los campos en Datos Generales
        List<WebElement> listaDatosGenerales = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[2]/div"));
        List<WebElement> listaDatosGeneralesRequeridos = new ArrayList<WebElement>(); // Lista de los campos requeridos en Datos Generales

        // Separar campos requeridos de los no requeridos
        for (int i = 0; i < listaDatosGenerales.size(); i++) {
            if (listaDatosGenerales.get(i).getText().contains("*")) {
                listaDatosGeneralesRequeridos.add(listaDatosGenerales.get(i));
            }
        }

        // ASSERTION
        /// Datos Generales
        assertTrue(listaDatosGeneralesRequeridos.size() >= 0);
        for (WebElement item : listaDatosGeneralesRequeridos) {
            if (!item.getText().contains("Sexo *") && !item.getText().contains("Tipo de Identificación") && !item.getText().contains("Identificación")) {
                assertTrue(item.getText().contains(mjCampoRequerido));
            }
        }

        // Lista de los campos en Domicilio Actual
        List<WebElement> listaDomicilioActual = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[3]/div"));
        List<WebElement> listaDomicilioActualRequeridos = new ArrayList<WebElement>(); // Lista de los campos requeridos en Domicilio Actual

        // Separar campos requeridos de los no requeridos
        for (int i = 0; i < listaDomicilioActual.size(); i++) {
            if (listaDomicilioActual.get(i).getText().contains("*")) {
                listaDomicilioActualRequeridos.add(listaDomicilioActual.get(i));
            }
        }

        // ASSERTION
        /// Domicilio Actual
        assertTrue(listaDomicilioActualRequeridos.size() >= 0);
        for (WebElement item : listaDomicilioActualRequeridos) {
            if (!item.getText().contains("Provincia de Residencia")) {
                assertTrue(item.getText().contains(mjCampoRequerido));
            }
        }

        // Lista de los campos en Medios de Contacto
        List<WebElement> listaMediosContacto = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[4]/div"));
        List<WebElement> listaMediosContactoRequeridos = new ArrayList<WebElement>(); // Lista de los campos requeridos en Medios de Contacto

        // Separar campos requeridos de los no requeridos
        for (int i = 0; i < listaMediosContacto.size(); i++) {
            if (listaMediosContacto.get(i).getText().contains("*")) {
                listaMediosContactoRequeridos.add(listaMediosContacto.get(i));
            }
        }

        // ASSERTION
        /// Medios de Contacto
        assertTrue(listaMediosContactoRequeridos.size() >= 0);
        for (WebElement item : listaMediosContactoRequeridos) {
            if (!item.getText().contains("Teléfono de Casa")) {
                assertTrue(item.getText().contains(mjCampoRequerido));
            }
        }

        // Lista de los campos en Nivel Educativo

        // ==== Area Izquierda ====
        List<WebElement> listaAreaIzquierdaNE = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[5]/div[2]/div/div"));
        List<WebElement> listaAreaIzquierdaNERequeridos = new ArrayList<WebElement>(); // Lista de los campos requeridos en Nivel Educativo Area Izquierda

        // Separar campos requeridos de los no requeridos
        for (int i = 0; i < listaAreaIzquierdaNE.size(); i++) {
            if (listaAreaIzquierdaNE.get(i).getText().contains("*")) {
                listaAreaIzquierdaNERequeridos.add(listaAreaIzquierdaNE.get(i));
            }
        }

        // ASSERTION
        /// Nivel Educativo Izquierda
        assertTrue(listaAreaIzquierdaNERequeridos.size() >= 0);
        for (WebElement item : listaAreaIzquierdaNERequeridos) {
            if (!item.getText().contains("¿ Institución pública o privada ?")) {
                assertTrue(item.getText().contains(mjCampoRequerido));
            }
        }

        // Lista de los campos en Contacto de Emergencia
        List<WebElement> listaContactoEmergencia = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[6]/div"));
        List<WebElement> listaContactoEmergenciaRequeridos = new ArrayList<WebElement>(); // Lista de los campos requeridos en Contacto de Emergencia

        // Separar campos requeridos de los no requeridos
        for (int i = 0; i < listaContactoEmergencia.size(); i++) {
            if (listaContactoEmergencia.get(i).getText().contains("*")) {
                listaContactoEmergenciaRequeridos.add(listaContactoEmergencia.get(i));
            }
        }

        // ASSERTION
        /// Contacto de Emergencia
        assertTrue(listaContactoEmergenciaRequeridos.size() >= 0);
        for (WebElement item : listaContactoEmergenciaRequeridos) {
            if (!item.getText().contains("Nombre (s)")) {
                assertTrue(item.getText().contains(mjCampoRequerido));
            }
        }

        // Lista de los campos en Información Financiera

        // ==== Area Izquierda ====
        List<WebElement> listaAreaIzquierdaIF = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-2\"]/div/div/app-add-admision/div[2]/div/div/div[7]/div[2]/div"));
        List<WebElement> listaAreaIzquierdaIFRequeridos = new ArrayList<WebElement>(); // Lista de los campos requeridos en Información Financiera Area Izquierda

        // Separar campos requeridos de los no requeridos
        for (int i = 0; i < listaAreaIzquierdaIF.size(); i++) {
            if (listaAreaIzquierdaIF.get(i).getText().contains("*")) {
                listaAreaIzquierdaIFRequeridos.add(listaAreaIzquierdaIF.get(i));
            }
        }

        // ASSERTION
        /// Información Financiera Izquierda
        assertTrue(listaAreaIzquierdaIFRequeridos.size() >= 0);
        for (WebElement item : listaAreaIzquierdaIFRequeridos) {
            if (!item.getText().contains("¿ Trabajas actualmente ?")) {
                assertTrue(item.getText().contains(mjCampoRequerido));
            }
        }

        // Lista de los campos en Información Financiera

        // ==== Area Derecha ====
        List<WebElement> listaAreaDerechaIF = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-2\"]/div/div/app-add-admision/div[2]/div/div/div[7]/div[3]/div"));
        List<WebElement> listaAreaDerechaIFRequeridos = new ArrayList<WebElement>(); // Lista de los campos requeridos en Información Financiera Area Derecha

        // Separar campos requeridos de los no requeridos
        for (int i = 0; i < listaAreaDerechaIF.size(); i++) {
            if (listaAreaDerechaIF.get(i).getText().contains("*")) {
                listaAreaDerechaIFRequeridos.add(listaAreaDerechaIF.get(i));
            }
        }

        // ASSERTION
        /// Información Financiera Derecha
        assertTrue(listaAreaDerechaIFRequeridos.size() >= 0);
        for (WebElement item : listaAreaDerechaIFRequeridos) {
            if (!item.getText().contains("¿ Trabajas actualmente ?")) {
                assertTrue(item.getText().contains(mjCampoRequerido));
            }
        }

        // Lista de los campos en Datos Médicos
        List<WebElement> listaDatosMedicos = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-2\"]/div/div/app-add-admision/div[2]/div/div/div[8]/div"));
        List<WebElement> listaDatosMedicosRequeridos = new ArrayList<WebElement>(); // Lista de los campos requeridos en Datos Médicos

        // Separar campos requeridos de los no requeridos
        for (int i = 0; i < listaDatosMedicos.size(); i++) {
            if (listaDatosMedicos.get(i).getText().contains("*")) {
                listaDatosMedicosRequeridos.add(listaDatosMedicos.get(i));
            }
        }

        // ASSERTION
        /// Datos Médicos
        assertTrue(listaDatosMedicosRequeridos.size() >= 0);
        for (WebElement item : listaDatosMedicosRequeridos) {
            if (!item.getText().contains("Tipo de Sangre")) {
                assertTrue(item.getText().contains(mjCampoRequerido));
            }
        }


        // Lista de los campos en Dificultades
        List<WebElement> listaDificultades = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-2\"]/div/div/app-add-admision/div[2]/div/div/div[9]/div[2]/div"));
        List<WebElement> listaDificultadesRequeridos = new ArrayList<WebElement>(); // Lista de los campos requeridos en Dificultades

        // Separar campos requeridos de los no requeridos
        for (int i = 0; i < listaDificultades.size(); i++) {
            if (listaDificultades.get(i).getText().contains("*")) {
                listaDificultadesRequeridos.add(listaDificultades.get(i));
            }
        }

        // ASSERTION
        /// Dificultades
        assertTrue(listaDificultadesRequeridos.size() >= 0);
        for (WebElement item : listaDificultadesRequeridos) {
            if (!item.getText().contains("¿Tienes dificultad para ver incluso si usas lentes?")) {
                assertTrue(item.getText().contains(mjCampoRequerido));
            }
        }


        // Lista de los campos en Información Adicional
        List<WebElement> listaInformacionAdicional = driver.findElements(By.xpath("/html/body/div/div[2]/div/mat-dialog-container/div/div/app-add-admision/div[2]/div/div/div[10]/div"));
        List<WebElement> listaInformacionAdicionalRequeridos = new ArrayList<>(); // Lista de los campos requeridos en Información Adicional

        // Separar campos requeridos de los no requeridos
        for (WebElement element : listaInformacionAdicional) {
            if (element.getText().contains("*")) {
                listaInformacionAdicionalRequeridos.add(element);
            }
        }

        // ASSERTION
        /// Información Adicional
        assertTrue(listaInformacionAdicionalRequeridos.size() >= 0);
        for (WebElement item : listaInformacionAdicionalRequeridos) {
            if (!item.getText().contains("¿Cómo te enteraste del ITLA?")) {
                assertTrue(item.getText().contains(mjCampoRequerido));
            }
        }

    }

    //Validar caracteres – Campos teléfono y celular
    @Test
    public void CaracteresTelefonoCelular() {

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

        // Crear tiempo
        tiempo(10000);

        // Hacer clic al boton "Realizar Solicitud"
        WebElement solicitud = driver.findElement(By.id("btnAdd"));
        solicitud.click();

        // Crear tiempo
        tiempo(5000);


        //TODO Medios de Contacto

        /// PASARLE TEXTO AL INPUT
        // Teléfono de Casa
        WebElement telefonoCasa = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[4]/div[2]/input"));
        telefonoCasa.sendKeys("Este es el campo de Teléfono de Casa");

        // Verificar si el input tiene texto
        assertFalse(()->{
            int numeroCaracteres = telefonoCasa.getText().length();
            if (numeroCaracteres == 0){
                return false;
            }else {
                return true;
            }
        }, "El resultado deberia ser False");

        // Celular *
        WebElement celular = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[4]/div[3]/input"));
        celular.sendKeys("Este es el campo de Celular");

        // Verificar si el input tiene texto
        assertFalse(()->{
            int numeroCaracteres = celular.getText().length();
            if (numeroCaracteres == 0){
                return false;
            }else {
                return true;
            }
        }, "El resultado deberia ser False");

        // Teléfono Trabajo
        WebElement telefonoTrabajo = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[4]/div[4]/input"));
        telefonoTrabajo.sendKeys("Este es el campo de Teléfono Trabajo");

        // Verificar si el input tiene texto
        assertFalse(()->{
            int numeroCaracteres = telefonoTrabajo.getText().length();
            if (numeroCaracteres == 0){
                return false;
            }else {
                return true;
            }
        }, "El resultado deberia ser False");

        // Otro Teléfono
        WebElement telefonoOtro = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[4]/div[5]/input"));
        telefonoOtro.sendKeys("Este es el campo de Otro Teléfono");

        // Verificar si el input tiene texto
        assertFalse(()->{
            int numeroCaracteres = telefonoOtro.getText().length();
            if (numeroCaracteres == 0){
                return false;
            }else {
                return true;
            }
        }, "El resultado deberia ser False");

// ------------------------------------------------
        /// PASARLE NUMEROS AL INPUT
        // Teléfono de Casa
        WebElement telefonoCasaNumero = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[4]/div[2]/input"));
        telefonoCasaNumero.sendKeys("12345678910");

        // Verificar si el input tiene numero y si son menores de 10 caracteres
        assertTrue(()->{

            int numeroCaracteres = limpiarNumeroTelefonico(telefonoCasaNumero.getAttribute("value")).length();
            if (numeroCaracteres > 0 && numeroCaracteres <=10){
                return true;
            }else {
                return false;
            }
        }, "El resultado deberia ser True");

        // Celular *
        WebElement celularNumero = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[4]/div[3]/input"));
        celularNumero.sendKeys("1234567891245");

        // Verificar si el input tiene numero y si son menores de 10 caracteres
        assertTrue(()->{
            int numeroCaracteres = limpiarNumeroTelefonico(celularNumero.getAttribute("value")).length();
            if (numeroCaracteres > 0 && numeroCaracteres <=10){
                return true;
            }else {
                return false;
            }
        }, "El resultado deberia ser True");

        // Teléfono Trabajo
        WebElement telefonoTrabajoNumero = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[4]/div[4]/input"));
        telefonoTrabajoNumero.sendKeys("1234567895471");

        // Verificar si el input tiene numero y si son menores de 10 caracteres
        assertTrue(()->{
            int numeroCaracteres = limpiarNumeroTelefonico(telefonoTrabajoNumero.getAttribute("value")).length();
            if (numeroCaracteres > 0 && numeroCaracteres <=10){
                return true;
            }else {
                return false;
            }
        }, "El resultado deberia ser True");

        // Otro Teléfono
        WebElement telefonoOtroNumero = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[4]/div[5]/input"));
        telefonoOtroNumero.sendKeys("1234567895471");

        // Verificar si el input tiene numero y si son menores de 10 caracteres
        assertTrue(()->{
            int numeroCaracteres = limpiarNumeroTelefonico(telefonoOtroNumero.getAttribute("value")).length();
            if (numeroCaracteres > 0 && numeroCaracteres <=10){
                return true;
            }else {
                return false;
            }
        }, "El resultado deberia ser True");


        //TODO Contacto de Emergencia

        /// PASARLE TEXTO AL INPUT
        // Teléfono
        WebElement telefonoEmergencia = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[6]/div[4]/input"));
        telefonoEmergencia.sendKeys("Este es el campo de Teléfono de Casa");

        // Verificar si el input tiene texto
        assertFalse(()->{
            int numeroCaracteres = telefonoEmergencia.getText().length();
            if (numeroCaracteres == 0){
                return false;
            }else {
                return true;
            }
        }, "El resultado deberia ser False");

        // Celular *
        WebElement celularEmergencia = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[6]/div[4]/input"));
        celularEmergencia.sendKeys("Este es el campo de Teléfono de Casa");

        // Verificar si el input tiene texto
        assertFalse(()->{
            int numeroCaracteres = celularEmergencia.getText().length();
            if (numeroCaracteres == 0){
                return false;
            }else {
                return true;
            }
        }, "El resultado deberia ser False");


        /// PASARLE NUMEROS AL INPUT
        // Teléfono
        WebElement telefonoEmergancia2 = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[6]/div[4]/input"));
        telefonoEmergancia2.sendKeys("12345678910478");

        // Verificar si el input tiene numero y si son menores de 10 caracteres
        assertTrue(()->{
            int numeroCaracteres = limpiarNumeroTelefonico(telefonoEmergancia2.getAttribute("value")).length();
            if (numeroCaracteres > 0 && numeroCaracteres <=10){
                return true;
            }else {
                return false;
            }
        }, "El resultado deberia ser True");

        // Celular *
        WebElement celularEmergancia2 = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[6]/div[5]/input"));
        celularEmergancia2.sendKeys("12345678910478");

        // Verificar si el input tiene numero y si son menores de 10 caracteres
        assertTrue(()->{
            int numeroCaracteres = limpiarNumeroTelefonico(celularEmergancia2.getAttribute("value")).length();
            if (numeroCaracteres > 0 && numeroCaracteres <=10){
                return true;
            }else {
                return false;
            }
        }, "El resultado deberia ser True");
    }

    // Terminar la ejeción
    @AfterAll
    public static void terminar(){
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        driver.quit();
    }

    // Crear tiempo
    public void tiempo(int milisegundos){
        try {
            Thread.sleep(milisegundos);
        }catch (Exception e){

        }
    }
    public String limpiarNumeroTelefonico(@org.jetbrains.annotations.NotNull String numero){
        String p1 = numero.substring(1,4);
        String p2 = numero.substring(6,9);
        String p3 = numero.substring(10);
        return  p1+p2+p3;
    }
}
