package automatization_registre;

import Entidades.CredencialesUsuario;
import Entidades.CuentaGoogle;
import Entidades.Pasaportes;
import Entidades.Usuario;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import com.google.gson.Gson;
import org.openqa.selenium.json.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class RegistroPersona {

    static Usuario usuarioRegistro = new Usuario();
    static List<String> identificadoresVentanas = new ArrayList<>();
    static int milisegundos = 7000;

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Darle 1 minuto a la sección para entroar elementos si no los encuentra la primea vez
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);

        // Datos del usuario
        datosUsuario();

        // Generar correo Tempail
        generarCorreoGeneradoTempail(driver, datosUsuario());

        // Loguear el usuario registrado
        login(driver, datosUsuario());
    }

    // Datos del usuario
    public static Usuario datosUsuario(){

        // Datos personales
        usuarioRegistro.nombre = "Lucas";
        usuarioRegistro.apellido = "Alcantara Soto";
        usuarioRegistro.numeroIdentidad = getPasaporte();
        usuarioRegistro.tipoDocumento = "Pasaporte";
        usuarioRegistro.credencialesLoginSigei.password = "lAS14789@";

        // Datos para la solicitud de admision
        usuarioRegistro.datosAdmision.recinto = "La Caleta, Boca Chica";
        usuarioRegistro.datosAdmision.periodoIngreso = "Enero-Abril 2025";
        usuarioRegistro.datosAdmision.tipoSolicitud = "Transferido";
        usuarioRegistro.datosAdmision.areaAcademica = "Tecnología en Desarrollo de Software";
        usuarioRegistro.datosAdmision.tandaEstudios = "Matutina (8:00am a 12:00m)";
        usuarioRegistro.datosAdmision.fechaNacimiento = "05-01-2001";
        usuarioRegistro.datosAdmision.lugarNacimiento = "Distrito Nacional";
        usuarioRegistro.datosAdmision.sexo = "Masculino";
        usuarioRegistro.datosAdmision.nacionalidad = "República Dominicana";
        usuarioRegistro.datosAdmision.estadoCivil = "Soltero(a)";
        usuarioRegistro.datosAdmision.provinciaResidencia = "Distrito Nacional";
        usuarioRegistro.datosAdmision.municipio = "Santo Domingo de Guzmán";
        usuarioRegistro.datosAdmision.sector = "Palma Real";
        usuarioRegistro.datosAdmision.telefonoCasa = "8094474444";
        usuarioRegistro.datosAdmision.celular = "8494748888";
        usuarioRegistro.datosAdmision.telefonoTrabajo = "8090004004";
        usuarioRegistro.datosAdmision.otroTelefono = "8494045550";
        usuarioRegistro.datosAdmision.correoTutor = "test.@gmail.com";
        usuarioRegistro.datosAdmision.instituciónSecundaria = "05251 - INSTITUTO PEDRO HENRIQUEZ UREÑA ( DISTRITO SANTO DOMINGO SURCENTRAL )";
        usuarioRegistro.datosAdmision.nivelAcceso = "Pública";
        usuarioRegistro.datosAdmision.desde = "09-10-2019";
        usuarioRegistro.datosAdmision.hasta = "09-10-2022";
        usuarioRegistro.datosAdmision.grado = "Bachiller Técnico";
        usuarioRegistro.datosAdmision.institucionUniversitaria = "(IGLOBAL)Instituto Global de Altos Estudios en Ciencias Sociales";
        usuarioRegistro.datosAdmision.universidadNivelAcceso = "Pública";
        usuarioRegistro.datosAdmision.universidaddesde = "09-10-2022";
        usuarioRegistro.datosAdmision.universidadhasta = "09-10-2023";
        usuarioRegistro.datosAdmision.universidadgrado = "Post-grado";
        usuarioRegistro.datosAdmision.nombre = "Laura Montesanto";
        usuarioRegistro.datosAdmision.telefono = "8495550000";
        usuarioRegistro.datosAdmision.parentesco = "Madre";
        usuarioRegistro.datosAdmision.celular = "8094474755";
        usuarioRegistro.datosAdmision.confirmacionTrabajo = "No";
        usuarioRegistro.datosAdmision.costeoEstudios = "Beca";
        usuarioRegistro.datosAdmision.dependeciaEconomica = "Si";
        usuarioRegistro.datosAdmision.cantidadDependientes = "2";
        usuarioRegistro.datosAdmision.ingresoMensual = "RD$4,500.00 - RD$9,999.00";
        usuarioRegistro.datosAdmision.tipoSangre = "B+";
        usuarioRegistro.datosAdmision.enterasteITLA = "Mailing list (Correo Electrónico)";
        usuarioRegistro.datosAdmision.usarasTransporteITLA = "Si";
        usuarioRegistro.datosAdmision.direccion = "Santo Domingo Este/Brisa";
        usuarioRegistro.datosAdmision.celularEmergencia = "8090009111";
        usuarioRegistro.datosAdmision.enfermedadesCongenitas = "";
        usuarioRegistro.datosAdmision.alergias = "Picadura abeja";
        usuarioRegistro.datosAdmision.discapacidades = "Discapacidad visual";
        usuarioRegistro.datosAdmision.origenEstudios = "República Dominicana";
        usuarioRegistro.datosAdmision.municipioNacimiento = "San Juan";
        usuarioRegistro.datosAdmision.dificultadVer = "No, ninguna dificultad";
        usuarioRegistro.datosAdmision.dificultadOir = "No, ninguna dificultad";
        usuarioRegistro.datosAdmision.dificultadCaminar = "No, ninguna dificultad";
        usuarioRegistro.datosAdmision.dificultadRecordar = "No, ninguna dificultad";
        usuarioRegistro.datosAdmision.dificultadTarea = "No, ninguna dificultad";
        usuarioRegistro.datosAdmision.dificultadComunicarse = "No, ninguna dificultad";

        return usuarioRegistro;
    }

    // Leer pasaportes
    public static Pasaportes getPasaportes(){
        Gson json = new Gson();
        Pasaportes pasaportes = new Pasaportes();
        String nombreArchivo = "Pasaportes.json";

        try {
            FileReader leerArchivo = new FileReader(nombreArchivo);
            pasaportes = json.fromJson(leerArchivo,Pasaportes.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return pasaportes;
    }

    // Crear registro automatizado SIGEI Academico
    public static void registroAutomatizadoSigei(WebDriver driver, Usuario usuarioRegistro){

        // Abrir nueva paestaña
        WebDriver nuevaVentana = driver.switchTo().newWindow(WindowType.TAB);

        // Navegar
        nuevaVentana.get("https://qasigeiacademico.itla.edu.do/account/login");

        // Agrear identificador a la lista
        identificadoresVentanas.add(nuevaVentana.getWindowHandle());

        // Click al btn registrar
        WebElement btnRegistrar = nuevaVentana.findElement(By.xpath("//*[@id=\"kt_body\"]/app-root/ng-component/ng-component/form/div/div/div/div/button"));
        btnRegistrar.click();

        // Hacer click al btn tipo de documento
        WebElement btnDocumentoIdentidad = nuevaVentana.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-register/div[2]/div/form/div/div[1]/div/ngx-select-dropdown/div/div/button"));
        btnDocumentoIdentidad.click();

        // Seleccion del tipo de documento de identidad
        seleccionTipoDocumento(usuarioRegistro.tipoDocumento, nuevaVentana);

        // Pasar al input de (No. de Documento) el número de identidad
        WebElement noDocumentoIdentidad = nuevaVentana.findElement(By.name("txtDocumento"));
        noDocumentoIdentidad.sendKeys(usuarioRegistro.numeroIdentidad);

        // Pasar correo al input Correo Electrónico y Confirmar Correo Electrónico
        WebElement correoElectronico = nuevaVentana.findElement(By.name("txtCorreo"));
        correoElectronico.sendKeys(usuarioRegistro.credencialesLoginSigei.email);

        WebElement confirmarCorreoElectronico = nuevaVentana.findElement(By.name("txtCorreoConfirm"));
        confirmarCorreoElectronico.sendKeys(usuarioRegistro.credencialesLoginSigei.email);

        // Pasar nombre y apellido a los input nombre y apellido
        WebElement nombreUser = nuevaVentana.findElement(By.name("txtNombre"));
        nombreUser.sendKeys(usuarioRegistro.nombre);

        WebElement apellidoUser = nuevaVentana.findElement(By.name("txtApellido"));
        apellidoUser.sendKeys(usuarioRegistro.apellido);

        // Pasar contraseña a los input Contraseña y Confirmar Contraseña
        WebElement userPassword = nuevaVentana.findElement(By.name("txtpassword"));
        userPassword.sendKeys(usuarioRegistro.credencialesLoginSigei.password);

        WebElement confirmPassword = nuevaVentana.findElement(By.name("txtconfirmPassword"));
        confirmPassword.sendKeys(usuarioRegistro.credencialesLoginSigei.password);

        // Hacar click en el botón registrarse
        WebElement btnRegistrase = nuevaVentana.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-register/div[2]/div/div/button"));
        btnRegistrase.click();

        // Hacer click en el botón Son Correctos
        WebElement btnCorrectos = nuevaVentana.findElement(By.xpath("//*[@id=\"kt_body\"]/div[2]/div/div[6]/button[1]"));
        btnCorrectos.click();

        // Tiempo en segundos
        tiempoEspera(milisegundos);

        // Hacer click en el boton ok
        WebElement btnOK = nuevaVentana.findElement(By.xpath("//*[@id=\"kt_body\"]/div[2]/div/div[6]/button[1]"));
        btnOK.click();

        // Inactivar pasaporte
        inactivarPasaporte(usuarioRegistro.numeroIdentidad);

        // Tiempo en segundos
        tiempoEspera(milisegundos);

        // Enfoque de la pestaña Tempail
        nuevaVentana.switchTo().window(identificadoresVentanas.get(0));

        // Confirmar solicitud
        confirmarSolicitudTempail(driver);
    }

    // Inactivar pasaportes usados
    public static void inactivarPasaporte(String idPasaporte){

        Gson json = new Gson();

        try {
            // Inactivar pasaporte
            String nombreArchivo = "Pasaportes.json";
            FileReader leerArchivo = new FileReader(nombreArchivo);
            Pasaportes pasaportes = getPasaportes();


            for (int i = 0; i<pasaportes.activos.size(); i++){
                if (pasaportes.activos.get(i).equals(idPasaporte)){
                    pasaportes.activos.remove(i);
                }
            }

            pasaportes.usados.add(idPasaporte);
            String pasaportesJson = json.toJson(pasaportes);
            FileWriter escribir = new FileWriter(nombreArchivo);
            escribir.write(pasaportesJson);
            escribir.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Obtener el pasaporte que usara el proximo usuario
    public static String getPasaporte(){
        Pasaportes pasaportes = getPasaportes();
         if (pasaportes.activos.size()>0){
             return pasaportes.activos.getFirst();
         }else {
             System.out.println("----------------------------------");
             System.out.println("Ya no hay pasaportes disponibles!!");
             System.out.println("----------------------------------");
             System.exit(0);
             return  null;
         }
    }

    // Seleccionar el tipo de documento
    public static void seleccionTipoDocumento(String tipoDocumento, WebDriver driver){
        if (tipoDocumento.equals("Cedula")){

            // Seleccionar el tipo de documento de identidad - Cedula
            WebElement tipoDocumentoCedula = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-register/div[2]/div/form/div/div[1]/div/ngx-select-dropdown/div/div[2]/div[3]/div[1]"));
            tipoDocumentoCedula.click();

        }else if(tipoDocumento.equals("Pasaporte")){

            // Seleccionar el tipo de documento de identidad - Pasaporte
            WebElement tipoDocumentoPasaporte = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-register/div[2]/div/form/div/div[1]/div/ngx-select-dropdown/div/div[2]/div[3]/div[2]"));
            tipoDocumentoPasaporte.click();

        }else {
            System.out.println("Tipo de documento mal especificado");
        }
    }

    //  Generar correo Tempail
    public static void generarCorreoGeneradoTempail(WebDriver driver, Usuario usuarioRegistro){

        // Ir a la ruta
        driver.get("https://tempail.com/es/#google_vignette");

        // Agrear identificador a la lista
        identificadoresVentanas.add(driver.getWindowHandle());

        // Seleccionar correo
        WebElement getCorreo = driver.findElement(By.id("eposta_adres"));
        String emailGenerado = getCorreo.getAttribute("data-clipboard-text");

        usuarioRegistro.credencialesLoginSigei.email = emailGenerado;

        registroAutomatizadoSigei(driver, usuarioRegistro);
    }

    // Confirmar solicitud en Tempail
    public static void confirmarSolicitudTempail(WebDriver driver){

        // Tiempo en segundos
        tiempoEspera(10000);

        // Refrescar correos
        WebElement btnRefrescar = driver.findElement(By.xpath("/html/body/section[1]/div[2]/div/div[4]/a[2]"));
        btnRefrescar.click();

        // Seleccionar el correo
        WebElement seleccionCorreo = driver.findElement(By.xpath("/html/body/section[2]/div/div/div/ul/li[2]/a"));
        seleccionCorreo.click();

        // Confirmar registro
        //WebElement btnConfirmarRegistro = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/a"));
        //btnConfirmarRegistro.click();

        // Tiempo en segundos
        tiempoEspera(milisegundos);
    }

    // Loguear al usuario;
    public static void  login(WebDriver driver_, Usuario usuarioRegistro){

        // Abrir otra pesataña
        WebDriver driver = driver_.switchTo().newWindow(WindowType.TAB);
        driver.get("https://qasigeiacademico.itla.edu.do/account/login");

        // Identificador pestaña
        identificadoresVentanas.add(driver.getWindowHandle());

        // Cambiar a la pestaña del sigei en el loguin
        driver.switchTo().window(identificadoresVentanas.get(2));

        // Introducir email (Usuario)
        WebElement usuarioInput = driver.findElement(By.id("email"));
        usuarioInput.sendKeys(usuarioRegistro.credencialesLoginSigei.email);

        // Introducir contraseña
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys(usuarioRegistro.credencialesLoginSigei.password);

        // Dar click al boton entrar
        WebElement btnLogin = driver.findElement(By.id("btnLogin"));
        btnLogin.click();

        // Registrar credenciales
        guardarCredenciales(usuarioRegistro);

        // Clikear solicitud admision y llenar datos
        admision(driver, usuarioRegistro);
    }

    // CREAR PROCESO DE ADMISION
    public static void admision(WebDriver driver, Usuario usuario){

        // Tiempo en segundos
        tiempoEspera(milisegundos);

        // Clickear btn Admisión Tecnologo
        WebElement btnAdmision = driver.findElement(By.xpath("//*[@id=\"kt_content\"]/div/div/div/div/div/div[2]/div[2]/div/div[1]/div[1]/a/div/div[1]"));
        btnAdmision.click();

        // Tiempo en segundos
        tiempoEspera(milisegundos);

        // Clickear btn Realizar solicitud
        WebElement btnAdd = driver.findElement(By.xpath("//*[@id=\"btnAdd\"]"));
        btnAdd.click();

            /// Mensaje de proceso de adminción cocluido
            try{
                WebElement admiCulminada = driver.findElement(By.xpath("//*[@id=\"swal2-title\"]"));
                System.out.println("-----------------------------------------------------------------------------------------------");
                System.out.println(admiCulminada.getText());
                System.out.println("-----------------------------------------------------------------------------------------------");
                System.out.println("\nEjecución finalizada...\n");
                System.exit(0);
            } catch (Exception e) {
                System.out.println("-----------------------------------------------------------------");
                System.out.println("No se pudo encontrar el mensaje del proceso de admision concluido");
                System.out.println("-----------------------------------------------------------------");
                System.out.println("Error: "+e);
            }

        // Seleccionar recinto
        WebElement btnRecinto = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[1]/div[2]/ngx-select-dropdown/div/button"));
        btnRecinto.click();

        List<WebElement> listaRecinto = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[1]/div[2]/ngx-select-dropdown/div/div/ul[2]/li"));
        int indexRecinto=0;
        for (int i = 0; i<listaRecinto.size(); i++){
            if(listaRecinto.get(i).getText().contains(usuario.datosAdmision.recinto)){
                indexRecinto = i;
            }
        }
        listaRecinto.get(indexRecinto).click();

        // Seleccionar Período de ingreso
        WebElement periodoIngreso = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[1]/div[3]/ngx-select-dropdown/div/button"));
        periodoIngreso.click();

        List<WebElement> listaPeriodoIngreso = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[1]/div[3]/ngx-select-dropdown/div/div/ul[2]/li"));
        int indexPeriodoIngreso=0;
        for (int i = 0; i<listaPeriodoIngreso.size(); i++){
            if(listaPeriodoIngreso.get(i).getText().contains(usuario.datosAdmision.periodoIngreso)){
                indexPeriodoIngreso = i;
            }
        }
        listaPeriodoIngreso.get(indexPeriodoIngreso).click();

        // Tipo de Solicitud
        WebElement tipoSolicitud = driver.findElement(By.xpath("/html/body/div/div[2]/div/mat-dialog-container/div/div/app-add-admision/div[2]/div/div/div[1]/div[4]/ngx-select-dropdown/div/button"));
        tipoSolicitud.click();

        List<WebElement> listaTipoSolicitud = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[1]/div[4]/ngx-select-dropdown/div/div/ul[2]/li"));
        int indexTipoSolicitud=0;
        for (int i = 0; i<listaTipoSolicitud.size(); i++){
            if(listaTipoSolicitud.get(i).getText().contains(usuario.datosAdmision.tipoSolicitud)){
                indexTipoSolicitud = i;
            }
        }
        listaTipoSolicitud.get(indexTipoSolicitud).click();

        // Área Académica
        WebElement areaAcademica = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[1]/div[5]/ngx-select-dropdown/div/button"));
        areaAcademica.click();

        List<WebElement> listaAreaAcademica = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[1]/div[5]/ngx-select-dropdown/div/div/ul[2]/li"));
        int indexAreaAcademica=0;
        for (int i = 0; i<listaAreaAcademica.size(); i++){
            if(listaAreaAcademica.get(i).getText().contains(usuario.datosAdmision.areaAcademica)){
                indexAreaAcademica = i;
            }
        }
        listaAreaAcademica.get(indexAreaAcademica).click();

        // Tiempo en segundos
        tiempoEspera(milisegundos);

        // Tanda de estudios de referencia
        WebElement tandaEstudios = driver.findElement(By.xpath("/html/body/div/div[2]/div/mat-dialog-container/div/div/app-add-admision/div[2]/div/div/div[1]/div[6]/ngx-select-dropdown/div/button"));
        tandaEstudios.click();

        List<WebElement> listaTandaEstudios = driver.findElements(By.xpath("/html/body/div/div[2]/div/mat-dialog-container/div/div/app-add-admision/div[2]/div/div/div[1]/div[6]/ngx-select-dropdown/div/div/ul[2]/li"));
        int indexTandaEstudios=0;
        for (int i = 0; i<listaTandaEstudios.size(); i++){
            if(listaTandaEstudios.get(i).getText().contains(usuario.datosAdmision.tandaEstudios)){
                indexTandaEstudios = i;
            }
        }
        listaTandaEstudios.get(indexTandaEstudios).click();

        // ------------ Datos Generales ------------

        // Fecha de Nacimiento
        WebElement fechaNacimiento = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[2]/div[2]/input"));
        fechaNacimiento.sendKeys(usuario.datosAdmision.fechaNacimiento);

        // Lugar de Nacimiento
        WebElement lugarNacimiento = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[2]/div[3]/ngx-select-dropdown/div/button"));
        lugarNacimiento.click();

        List<WebElement> listaLugarNacimiento = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[2]/div[3]/ngx-select-dropdown/div/div/ul[2]/li"));
        int indexLugarNacimiento=0;
        for (int i = 0; i<listaLugarNacimiento.size(); i++){
            if(listaLugarNacimiento.get(i).getText().contains(usuario.datosAdmision.lugarNacimiento)){
                indexLugarNacimiento = i;
            }
        }
        listaLugarNacimiento.get(indexLugarNacimiento).click();

        // Sexo
        WebElement sexo = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[2]/div[4]/ngx-select-dropdown/div/button"));
        sexo.click();

        List<WebElement> listaSexo = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[2]/div[4]/ngx-select-dropdown/div/div/ul[2]/li"));
        int indexSexo=0;
        for (int i = 0; i<listaSexo.size(); i++){
            if(listaSexo.get(i).getText().contains(usuario.datosAdmision.sexo)){
                indexSexo = i;
            }
        }
        listaSexo.get(indexSexo).click();

        // Nacionalidad
        WebElement nacionalidad = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[2]/div[5]/ngx-select-dropdown/div/button"));
        nacionalidad.click();

        List<WebElement> listaNacionalidad = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[2]/div[5]/ngx-select-dropdown/div/div/ul[2]/li"));
        int indexNacionalidad=0;
        for (int i = 0; i<listaNacionalidad.size(); i++){
            if(listaNacionalidad.get(i).getText().contains(usuario.datosAdmision.nacionalidad)){
                indexNacionalidad = i;
            }
        }
        listaNacionalidad.get(indexNacionalidad).click();

        // Estado Civil
        WebElement estadoCivil = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[2]/div[6]/ngx-select-dropdown/div/button"));
        estadoCivil.click();

        List<WebElement> listaEstadoCivil= driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[2]/div[6]/ngx-select-dropdown/div/div/ul[2]/li"));
        int indexEstadoCivil=0;
        for (int i = 0; i<listaEstadoCivil.size(); i++){
            if(listaEstadoCivil.get(i).getText().contains(usuario.datosAdmision.estadoCivil)){
                indexEstadoCivil = i;
            }
        }
        listaEstadoCivil.get(indexEstadoCivil).click();

        // País Origen de Estudios
        WebElement origenEstudios = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[2]/div[9]/ngx-select-dropdown/div/button"));
        origenEstudios.click();

        List<WebElement> listaOrigenEstudios = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[2]/div[9]/ngx-select-dropdown/div/div/ul[2]/li"));
        int indexOrigenEstudios =0;
        for (int i = 0; i< listaOrigenEstudios.size(); i++){
            if(listaOrigenEstudios.get(i).getText().contains(usuario.datosAdmision.origenEstudios)){
                indexOrigenEstudios = i;
            }
        }
        listaOrigenEstudios.get(indexOrigenEstudios).click();

        // Municipio de Nacimiento
        WebElement municipioNacimiento = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[2]/div[10]/ngx-select-dropdown/div/button"));
        municipioNacimiento.click();

        List<WebElement> listaMunicipioNacimiento = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[2]/div[10]/ngx-select-dropdown/div/div/ul[2]/li"));
        int indexMunicipioNacimiento =0;
        for (int i = 0; i< listaMunicipioNacimiento.size(); i++){
            if(listaMunicipioNacimiento.get(i).getText().contains(usuario.datosAdmision.municipioNacimiento)){
                indexMunicipioNacimiento = i;
            }
        }
        listaMunicipioNacimiento.get(indexMunicipioNacimiento).click();

        // ------------- Domicilio Actual --------------

        // Provincia de Residencia
        WebElement provinciaRecidencia = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[3]/div[2]/ngx-select-dropdown/div/button"));
        provinciaRecidencia.click();

        List<WebElement> listaProvinciaRecidencia= driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[3]/div[2]/ngx-select-dropdown/div/div/ul[2]/li"));
        int indexProvinciaRecidencia=0;
        for (int i = 0; i<listaProvinciaRecidencia.size(); i++){
            if(listaProvinciaRecidencia.get(i).getText().contains(usuario.datosAdmision.provinciaResidencia)){
                indexProvinciaRecidencia = i;
            }
        }
        listaProvinciaRecidencia.get(indexProvinciaRecidencia).click();

        // Municipio
        WebElement municipio = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[3]/div[3]/ngx-select-dropdown/div/button"));
        municipio.click();

        List<WebElement> listaMunicipio= driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[3]/div[3]/ngx-select-dropdown/div/div/ul[2]/li"));
        int indexMunicipio=0;
        for (int i = 0; i<listaMunicipio.size(); i++){
            if(listaMunicipio.get(i).getText().contains(usuario.datosAdmision.municipio)){
                indexMunicipio = i;
            }
        }
        listaMunicipio.get(indexMunicipio).click();

        // Sector
        WebElement sector = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[3]/div[4]/ngx-select-dropdown/div/button"));
        sector.click();

        List<WebElement> listaSector= driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[3]/div[4]/ngx-select-dropdown/div/div/ul[2]/li"));
        int indexSector=0;
        for (int i = 0; i<listaSector.size(); i++){
            if(listaSector.get(i).getText().contains(usuario.datosAdmision.sector)){
                indexSector = i;
            }
        }
        listaSector.get(indexSector).click();

        // Dirección
        WebElement direccion = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[3]/div[5]/input"));
        direccion.sendKeys(usuario.datosAdmision.direccion);

        // ------------ Medios de Contacto -------------

        // Teléfono de Casa
        WebElement telefono = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[4]/div[2]/input"));
        telefono.sendKeys(usuario.datosAdmision.telefonoCasa);

        // Celular
        WebElement celular = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[4]/div[3]/input"));
        celular.sendKeys(usuario.datosAdmision.celular);

        // Teléfono Trabajo
        WebElement telefonoTrabajo = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[4]/div[4]/input"));
        telefonoTrabajo.sendKeys(usuario.datosAdmision.telefonoTrabajo);

        // Otro Teléfono
        WebElement otroTelefono = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[4]/div[5]/input"));
        otroTelefono.sendKeys(usuario.datosAdmision.otroTelefono);

        // Correo Tutor
        WebElement correoTutor = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[4]/div[6]/input"));
        correoTutor.sendKeys(usuario.datosAdmision.correoTutor);

        // -------------- Nivel Educativo --------------

        // Institución Secundaria
        WebElement institucionSecundaria = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[5]/div[2]/div/div[1]/div/ng-autocomplete/div[1]/div[1]/input"));
        institucionSecundaria.sendKeys(usuario.datosAdmision.instituciónSecundaria);

        List<WebElement> listaInstitucionSecundaria = driver.findElements(By.xpath("//*[@id=\"suggestions\"]/ul/li"));
        int indexInstitucionSecundaria=0;
        for (int i = 0; i<listaInstitucionSecundaria.size(); i++){
            if(listaInstitucionSecundaria.get(i).getText().contains(usuario.datosAdmision.instituciónSecundaria)){
                indexInstitucionSecundaria = i;
            }
        }
        listaInstitucionSecundaria.get(indexInstitucionSecundaria).click();

        // ¿ Institución pública o privada ?
        WebElement nivelAcceso = driver.findElement(By.xpath("/html/body/div/div[2]/div/mat-dialog-container/div/div/app-add-admision/div[2]/div/div/div[5]/div[2]/div/div[2]/ngx-select-dropdown/div/button"));
        nivelAcceso.click();

        List<WebElement> listaNivelAcceso= driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[5]/div[2]/div/div[2]/ngx-select-dropdown/div/div/ul[2]/li"));
        int indexNivelAcceso=0;
        for (int i = 0; i<listaNivelAcceso.size(); i++){
            if(listaNivelAcceso.get(i).getText().contains(usuario.datosAdmision.nivelAcceso)){
                indexNivelAcceso = i;
            }
        }
        listaNivelAcceso.get(indexNivelAcceso).click();

        // Desde
        WebElement desde = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[5]/div[2]/div/div[3]/input"));
        desde.sendKeys(usuario.datosAdmision.desde);

        // Hasta
        WebElement hasta  = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[5]/div[2]/div/div[4]/input"));
        hasta.sendKeys(usuario.datosAdmision.hasta);

        // Grado
        WebElement grado = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[5]/div[2]/div/div[5]/ngx-select-dropdown/div/button"));
        grado.click();

        List<WebElement> listaGrado = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[5]/div[2]/div/div[5]/ngx-select-dropdown/div/div/ul[2]/li"));
        int indexGrado=0;
        for (int i = 0; i<listaGrado.size(); i++){
            if(listaGrado.get(i).getText().contains(usuario.datosAdmision.grado)){
                indexGrado = i;
            }
        }
        listaGrado.get(indexGrado).click();

        // Institución Universitaria
        WebElement institucionUniversitaria = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[5]/div[3]/div/div[1]/ngx-select-dropdown/div/button"));
        institucionUniversitaria.click();

        List<WebElement> listaInstitucionUniversitaria = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[5]/div[3]/div/div[1]/ngx-select-dropdown/div/div/ul[2]/li"));
        int indexInstitucionUniversitaria=0;
        for (int i = 0; i<listaInstitucionUniversitaria.size(); i++){
            if(listaInstitucionUniversitaria.get(i).getText().contains(usuario.datosAdmision.institucionUniversitaria)){
                indexInstitucionUniversitaria = i;
            }
        }
        listaInstitucionUniversitaria.get(indexInstitucionUniversitaria).click();

        // ¿ Institución pública o privada ? Universidad
        WebElement nivelAccesoUniversidad = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[5]/div[3]/div/div[2]/ngx-select-dropdown/div/button"));
        nivelAccesoUniversidad.click();

        List<WebElement> listaNivelAccesoUniversidad= driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[5]/div[3]/div/div[2]/ngx-select-dropdown/div/div/ul[2]/li"));
        int indexNivelAccesoUniversidad=0;
        for (int i = 0; i<listaNivelAccesoUniversidad.size(); i++){
            if(listaNivelAccesoUniversidad.get(i).getText().contains(usuario.datosAdmision.universidadNivelAcceso)){
                indexNivelAccesoUniversidad = i;
            }
        }
        listaNivelAccesoUniversidad.get(indexNivelAccesoUniversidad).click();

        // Desde
        WebElement universidaddesde = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[5]/div[3]/div/div[3]/input"));
        universidaddesde.sendKeys(usuario.datosAdmision.universidaddesde);

        // Hasta
        WebElement universidadhasta  = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[5]/div[3]/div/div[4]/input"));
        universidadhasta.sendKeys(usuario.datosAdmision.universidadhasta);

        // Grado
        WebElement universidadgradogrado = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[5]/div[3]/div/div[5]/ngx-select-dropdown/div/button"));
        universidadgradogrado.click();

        List<WebElement> listaUniversidadgradogrado = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[5]/div[3]/div/div[5]/ngx-select-dropdown/div/div/ul[2]/li"));
        int indexUniversidadgradogrado=0;
        for (int i = 0; i<listaUniversidadgradogrado.size(); i++){
            if(listaUniversidadgradogrado.get(i).getText().contains(usuario.datosAdmision.universidadgrado)){
                indexUniversidadgradogrado = i;
            }
        }
        listaUniversidadgradogrado.get(indexUniversidadgradogrado).click();

        // --------------- Contacto de Emergencia ----------------

        // Nombre
        WebElement nombre = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[6]/div[2]/input"));
        nombre.sendKeys(usuario.datosAdmision.nombre);

        // Parentesco
        WebElement parentesco = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[6]/div[3]/ngx-select-dropdown/div/button"));
        parentesco.click();

        List<WebElement> listaParentesco = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[6]/div[3]/ngx-select-dropdown/div/div/ul[2]/li"));
        int indexParentesco=0;
        for (int i = 0; i<listaParentesco.size(); i++){
            if(listaParentesco.get(i).getText().contains(usuario.datosAdmision.parentesco)){
                indexParentesco = i;
            }
        }
        listaParentesco.get(indexParentesco).click();

        // Teléfono
        WebElement telef = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[6]/div[4]/input"));
        telef.sendKeys(usuario.datosAdmision.telefono);

        // Celular
        WebElement celularEmergencia = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[6]/div[5]/input"));
        celularEmergencia.sendKeys(usuario.datosAdmision.celularEmergencia);

        // -------------- Información Financiera --------------

        // ¿ Trabajas actualmente ?
        WebElement confirmacionTrabajo = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[7]/div[2]/div[1]/ngx-select-dropdown/div/button"));
        confirmacionTrabajo.click();

        List<WebElement> listaConfirmacionTrabajo = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[7]/div[2]/div[1]/ngx-select-dropdown/div/div/ul[2]/li"));
        int indexConfirmacionTrabajo=0;
        for (int i = 0; i<listaConfirmacionTrabajo.size(); i++){
            if(listaConfirmacionTrabajo.get(i).getText().contains(usuario.datosAdmision.confirmacionTrabajo)){
                indexConfirmacionTrabajo = i;
            }
        }
        listaConfirmacionTrabajo.get(indexConfirmacionTrabajo).click();

        // ¿ Cómo costearás tus estudios ?
        WebElement costeoEstudios = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[7]/div[2]/div[2]/ngx-select-dropdown/div/button"));
        costeoEstudios.click();

        List<WebElement> listaCosteoEstudios = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[7]/div[2]/div[2]/ngx-select-dropdown/div/div/ul[2]/li"));
        int indexCosteoEstudios=0;
        for (int i = 0; i<listaCosteoEstudios.size(); i++){
            if(listaCosteoEstudios.get(i).getText().contains(usuario.datosAdmision.costeoEstudios)){
                indexCosteoEstudios = i;
            }
        }
        listaCosteoEstudios.get(indexCosteoEstudios).click();

        // ¿ Depende alguien económicamente de ti ?
        WebElement dependeciaEconomica = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[7]/div[3]/div[1]/ngx-select-dropdown/div/button"));
        dependeciaEconomica.click();

        List<WebElement> listaDependeciaEconomica= driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[7]/div[3]/div[1]/ngx-select-dropdown/div/div/ul[2]/li"));
        int indexDependeciaEconomica=0;
        for (int i = 0; i<listaDependeciaEconomica.size(); i++){
            if(listaDependeciaEconomica.get(i).getText().contains(usuario.datosAdmision.dependeciaEconomica)){
                indexDependeciaEconomica = i;
            }
        }
        listaDependeciaEconomica.get(indexDependeciaEconomica).click();

        // Cantidad de dependientes
        String xPathElementoIngresoMensual = "//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[7]/div[3]/div[2]/ngx-select-dropdown/div/button";
        String xPathElementoIngresoMensualItem = "//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[7]/div[3]/div[2]/ngx-select-dropdown/div/div/ul[2]/li";
        if (usuario.datosAdmision.dependeciaEconomica.equals("Si")){
            WebElement cantidadDependientes = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[7]/div[3]/div[2]/input"));
            cantidadDependientes.sendKeys(usuario.datosAdmision.cantidadDependientes);
            xPathElementoIngresoMensual = "//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[7]/div[3]/div[3]/ngx-select-dropdown/div/button";
            xPathElementoIngresoMensualItem = "//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[7]/div[3]/div[3]/ngx-select-dropdown/div/div/ul[2]/li";
        }

        // Ingreso Mensual
        WebElement ingresoMensual = driver.findElement(By.xpath(xPathElementoIngresoMensual));
        ingresoMensual.click();

        List<WebElement> listaIngresoMensual = driver.findElements(By.xpath(xPathElementoIngresoMensualItem));
        int indexIngresoMensual=0;
        for (int i = 0; i<listaIngresoMensual.size(); i++){
            if(listaIngresoMensual.get(i).getText().contains(usuario.datosAdmision.ingresoMensual)){
                indexIngresoMensual = i;
            }
        }
        listaIngresoMensual.get(indexIngresoMensual).click();

        // -------------- Datos Médicos ----------------

        // Ingreso Mensual
        WebElement tipoSangre = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[8]/div[2]/ngx-select-dropdown/div/button"));
        tipoSangre.click();

        List<WebElement> listaTipoSangre = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[8]/div[2]/ngx-select-dropdown/div/div/ul[2]/li"));
        int indexTipoSangre=0;
        for (int i = 0; i<listaTipoSangre.size(); i++){
            if(listaTipoSangre.get(i).getText().contains(usuario.datosAdmision.tipoSangre)){
                indexTipoSangre = i;
            }
        }
        listaTipoSangre.get(indexTipoSangre).click();

        // Enfermedades congénitas
        WebElement enfermedadesCongenitas = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[8]/div[3]/ng-multiselect-dropdown/div/div[1]/span"));
        enfermedadesCongenitas.click();

        List<WebElement> listaEnfermedadesCongenitas = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[8]/div[3]/ng-multiselect-dropdown/div/div[2]/ul[2]/li"));
        WebElement todasEnfermedadesCongenitas = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[8]/div[3]/ng-multiselect-dropdown/div/div[2]/ul[1]/li[1]"));

        listaEnfermedadesCongenitas.add(todasEnfermedadesCongenitas);

        for (int i = 0; i< listaEnfermedadesCongenitas.size(); i++){
            if(listaEnfermedadesCongenitas.get(i).getText().contains(usuario.datosAdmision.enfermedadesCongenitas)){
                listaEnfermedadesCongenitas.get(i).click();
            }
        }

        // Alergias
        WebElement alergias = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[8]/div[4]/ng-multiselect-dropdown/div/div[1]/span"));
        alergias.click();

        List<WebElement> listaAlergias = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[8]/div[4]/ng-multiselect-dropdown/div/div[2]/ul[2]/li"));
        WebElement todasAlergias = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[8]/div[4]/ng-multiselect-dropdown/div/div[2]/ul[1]/li[1]"));

        listaAlergias.add(todasAlergias);

        for (int i = 0; i< listaAlergias.size(); i++){
            if(listaAlergias.get(i).getText().contains(usuario.datosAdmision.alergias)){
                listaAlergias.get(i).click();
            }
        }

        // Discapacidades
        WebElement discapacidades = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[8]/div[5]/ng-multiselect-dropdown/div/div[1]/span"));
        discapacidades.click();

        List<WebElement> listaDiscapacidades= driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[8]/div[5]/ng-multiselect-dropdown/div/div[2]/ul[2]/li"));
        WebElement todasDiscapacidades = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[8]/div[5]/ng-multiselect-dropdown/div/div[2]/ul[1]/li[1]"));

        listaDiscapacidades.add(todasDiscapacidades);

        for (int i = 0; i< listaDiscapacidades.size(); i++){
            if(listaDiscapacidades.get(i).getText().contains(usuario.datosAdmision.discapacidades)){
                listaDiscapacidades.get(i).click();
            }
        }

        /// Seccion en proceso de desarrollo
        // -------------- Dificultades ---------------

        // ¿Tienes dificultad para ver incluso si usas lentes?
        WebElement dificultadVer = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[9]/div[2]/div[1]/ngx-select-dropdown/div/button"));
        dificultadVer.click();

        List<WebElement> listaDificultadVer = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[9]/div[2]/div[1]/ngx-select-dropdown/div/div/ul[2]/li"));
        int indexDificultadVer = 0;
        for (int i = 0; i< listaDificultadVer.size(); i++){
            if(listaDificultadVer.get(i).getText().contains(usuario.datosAdmision.dificultadVer)){
                indexDificultadVer = i;
            }
        }
        listaDificultadVer.get(indexDificultadVer).click();

        // ¿Tienes dificultad para oír incluso con aparato auditivo?
        WebElement dificultadOir = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[9]/div[2]/div[2]/ngx-select-dropdown/div/button"));
        dificultadOir.click();

        List<WebElement> listaDificultadOir = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[9]/div[2]/div[2]/ngx-select-dropdown/div/div/ul[2]/li"));
        int indexDificultadOir = 0;
        for (int i = 0; i< listaDificultadOir.size(); i++){
            if(listaDificultadOir.get(i).getText().contains(usuario.datosAdmision.dificultadOir)){
                indexDificultadOir = i;
            }
        }
        listaDificultadOir.get(indexDificultadOir).click();

        // ¿Tienes dificultad para caminar o subir escalones?
        WebElement dificultadCaminar = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[9]/div[2]/div[3]/ngx-select-dropdown/div/button"));
        dificultadCaminar.click();

        List<WebElement> listaDificultadCaminar = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[9]/div[2]/div[3]/ngx-select-dropdown/div/div/ul[2]/li"));
        int indexDificultadCaminar = 0;
        for (int i = 0; i< listaDificultadCaminar.size(); i++){
            if(listaDificultadCaminar.get(i).getText().contains(usuario.datosAdmision.dificultadCaminar)){
                indexDificultadCaminar = i;
            }
        }
        listaDificultadCaminar.get(indexDificultadCaminar).click();

        // ¿Tienes dificultad para recordar o concentrarse?
        WebElement dificultadRecordar = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[9]/div[2]/div[4]/ngx-select-dropdown/div/button"));
        dificultadRecordar.click();

        List<WebElement> listaDificultadRecordar = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[9]/div[2]/div[4]/ngx-select-dropdown/div/div/ul[2]/li"));
        int indexDificultadRecordar = 0;
        for (int i = 0; i< listaDificultadRecordar.size(); i++){
            if(listaDificultadRecordar.get(i).getText().contains(usuario.datosAdmision.dificultadRecordar)){
                indexDificultadRecordar = i;
            }
        }
        listaDificultadRecordar.get(indexDificultadRecordar).click();

        // ¿Tienes dificultad para realizar tareas (requiere soporte)?
        WebElement dificultadTarea = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[9]/div[2]/div[5]/ngx-select-dropdown/div/button"));
        dificultadTarea.click();

        List<WebElement> listaDificultadTarea = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[9]/div[2]/div[5]/ngx-select-dropdown/div/div/ul[2]/li"));
        int indexDificultadTarea = 0;
        for (int i = 0; i< listaDificultadTarea.size(); i++){
            if(listaDificultadTarea.get(i).getText().contains(usuario.datosAdmision.dificultadTarea)){
                indexDificultadTarea = i;
            }
        }
        listaDificultadTarea.get(indexDificultadTarea).click();

        // ¿Tienes dificultad para comunicarse (entender y ser entendido)?
        WebElement dificultadComunicarse = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[9]/div[2]/div[6]/ngx-select-dropdown/div/button"));
        dificultadComunicarse.click();

        List<WebElement> listaDificultadComunicarse = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[9]/div[2]/div[6]/ngx-select-dropdown/div/div/ul[2]/li"));
        int indexDificultadComunicarse = 0;
        for (int i = 0; i< listaDificultadComunicarse.size(); i++){
            if(listaDificultadComunicarse.get(i).getText().contains(usuario.datosAdmision.dificultadComunicarse)){
                indexDificultadComunicarse = i;
            }
        }
        listaDificultadComunicarse.get(indexDificultadComunicarse).click();

        // -------------- Información Adicional ---------------

        // ¿ Cómo te enteraste del ITLA ?
        WebElement enterasteITLA = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[10]/div[2]/ngx-select-dropdown/div/button"));
        enterasteITLA.click();

        List<WebElement> listaEnterasteITLA = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[10]/div[2]/ngx-select-dropdown/div/div/ul[2]/li"));
        int indexEnterasteITLA=0;
        for (int i = 0; i<listaEnterasteITLA.size(); i++){
            if(listaEnterasteITLA.get(i).getText().contains(usuario.datosAdmision.enterasteITLA)){
                indexEnterasteITLA = i;
            }
        }
        listaEnterasteITLA.get(indexEnterasteITLA).click();

        // ¿ Usarás el transporte de la institución ?
        WebElement usarasTransporteITLA = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[10]/div[3]/ngx-select-dropdown/div/button"));
        usarasTransporteITLA.click();

        List<WebElement> listaUsarasTransporteITLA = driver.findElements(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[2]/div/div/div[10]/div[3]/ngx-select-dropdown/div/div/ul[2]/li"));
        int indexUsarasTransporteITLA=0;
        for (int i = 0; i<listaUsarasTransporteITLA.size(); i++){
            if(listaUsarasTransporteITLA.get(i).getText().contains(usuario.datosAdmision.usarasTransporteITLA)){
                indexUsarasTransporteITLA = i;
            }
        }
        listaUsarasTransporteITLA.get(indexUsarasTransporteITLA).click();

        // Tiempo en segundos
        tiempoEspera(milisegundos);

        // Clickear el boton guardar
        WebElement btnGuardar = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-add-admision/div[3]/button[2]"));
        btnGuardar.click();

        // Tiempo en segundos
        tiempoEspera(milisegundos);

        // Clickear btn Si, Son Correctos!
        WebElement btnVerificacionDatos = driver.findElement(By.xpath("//*[@id=\"kt_body home-modal\"]/div[2]/div/div[6]/button[1]"));
        btnVerificacionDatos.click();

        // Tiempo en segundos
        tiempoEspera(milisegundos);

        // Clikear boton Ok de solicitud agregada exitosamente
        WebElement btnOkSolicExitosa = driver.findElement(By.xpath("//*[@id=\"kt_body home-modal\"]/div[2]/div/div[6]/button[1]"));
        btnOkSolicExitosa.click();
    }

    // Crear un tiempo de espera
    public static void tiempoEspera(int milisegundos){
        try {
            Thread.sleep(milisegundos);
        } catch (Exception e) {
            System.out.println("No se pudo aplicar el tiempo de espera, error: "+e);
        }
    }

    // Guardar credenciales de los usarios registrados
    public static  void guardarCredenciales(Usuario usuario){

        String nombreArchivoRegristros = "Credenciales_usuarios_registrados.json";
        Gson json = new Gson();

        // Crear instancia del archivo
        File file = new File(nombreArchivoRegristros);

        // Creando intancia de CredencialesUsuario
        CredencialesUsuario credencialesUsuario = new CredencialesUsuario();

        if (!new File(nombreArchivoRegristros).exists()){

            // Pasando valores a CredencialesUsuario
            credencialesUsuario.numeroIdentidad = usuario.numeroIdentidad;
            credencialesUsuario.nombre = usuario.nombre;
            credencialesUsuario.apellido = usuario.apellido;
            credencialesUsuario.email = usuario.credencialesLoginSigei.email;
            credencialesUsuario.password = usuario.credencialesLoginSigei.password;

            // Creando lista de CredencialesUsuario
            ArrayList<CredencialesUsuario> listaCredenciales = new ArrayList<CredencialesUsuario>();
            listaCredenciales.add(credencialesUsuario);

            // Creando string del json
            String credencialesJson = json.toJson(listaCredenciales);

            // Crear archivo
            try {
                file.createNewFile();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            // Escribir archivo
            try {
                FileWriter fileWriter = new FileWriter(nombreArchivoRegristros);
                fileWriter.write(credencialesJson);
                fileWriter.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else {

            try {
                // Leer archivo
                FileReader fileReader = new FileReader(nombreArchivoRegristros);

                // Convertir a objeto
                Type listType = new TypeToken<ArrayList<CredencialesUsuario>>() {}.getType();
                ArrayList<CredencialesUsuario> listCredenciales = json.fromJson(fileReader, listType);

                // Dandole valores a CredencialesUsuario
                credencialesUsuario.numeroIdentidad = usuario.numeroIdentidad;
                credencialesUsuario.nombre = usuario.nombre;
                credencialesUsuario.apellido = usuario.apellido;
                credencialesUsuario.email = usuario.credencialesLoginSigei.email;
                credencialesUsuario.password = usuario.credencialesLoginSigei.password;

                // Agregar objeto a la lista de credenciales
                listCredenciales.add(credencialesUsuario);

                // Creando string del json
                String credencialesJson = json.toJson(listCredenciales);

                // Escribir archivo
                try {
                    FileWriter fileWriter = new FileWriter(nombreArchivoRegristros);
                    fileWriter.write(credencialesJson);
                    fileWriter.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Generar acrchivos json con datos
    public static void generarArchivoJson(String nombreArchivo, CuentaGoogle cuentaGoogle){
        Gson json = new Gson();

        File file = new File(nombreArchivo);

        // Verificar, si el archivo no existe que cree uno con los datos
        if (!file.exists()){
            try {
                List<CuentaGoogle> listCuentaGoogle = new ArrayList<>();
                listCuentaGoogle.add(cuentaGoogle);

                // Convertir a json
                String datosJson = json.toJson(listCuentaGoogle);

                // Escribir y crear el archivo
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(datosJson);
                fileWriter.close();
                file.createNewFile();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else {
            // Leer y reescribir archivo
            try {
                // Leer archivo
                FileReader reader = new FileReader(nombreArchivo);

                // Crear el tipo de objeto del json y comvertir el json a objeto
                Type tipoJson = new  TypeToken<List<CuentaGoogle>>() {}.getType();
                List<CuentaGoogle> listCuentas = json.fromJson(reader, tipoJson);

                // Agregar un objeto a la lista
                listCuentas.add(cuentaGoogle);

                // Comvertir lista de objeto a texto
                String datosJson = json.toJson(listCuentas);

                // Escribir en el archivo
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(datosJson);
                fileWriter.close();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /// ====== NO SE ESTA USUANDO ======
    // Esta funcion logea un usuario en gmail y confirma la solicitud del corre Registro itla
    public static void administrarGmail(WebDriver driver, String gmail, String password){
        String url = "https://accounts.google.com/InteractiveLogin/signinchooser?continue=https%3A%2F%2Fmail.google.com%2Fmail%2Fu%2F0%2F&emr=1&followup=https%3A%2F%2Fmail.google.com%2Fmail%2Fu%2F0%2F&osid=1&passive=1209600&service=mail&ifkv=ARpgrqdXfDkRktSpu5dPAvhZtZXqVP4nNFmv8GyY8mQLd9sUuGWDazAC3y5DkEh6NiQTsH4dlh9pPw&ddm=0&flowName=GlifWebSignIn&flowEntry=ServiceLogin";
        driver.get(url);

        // Introducir correo
        WebElement inputCorreo = driver.findElement(By.id("identifierId"));
        inputCorreo.sendKeys(gmail);

        // Clikear boton siguiente
        WebElement btnSiguiente = driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/div/button/span"));
        btnSiguiente.click();

        // Introducir contraseña
        WebElement inputPass = driver.findElement(By.name("Passwd"));
        inputPass.sendKeys(password);

        // Clikear boton siguiente
        WebElement btnSiguiente2 = driver.findElement(By.xpath("//*[@id=\"passwordNext\"]/div/button/span"));
        btnSiguiente2.click();

        // Clikear correo
        WebElement Correo = driver.findElement(By.xpath("//*[@id=\":2g\"]"));
        Correo.click();

        // Confirmar solicitud
        WebElement Confirmar = driver.findElement(By.xpath("//*[@id=\":6l\"]/div[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/a"));
        Confirmar.click();
    }

    /// ====== NO SE ESTA USUANDO ======
    // Generar correos de Gmail
    public static void generarCorreosGmail(int cantidadCorreo, String nombreCuenta, String diaCuenta, String mesCuenta, String anioCuenta, String generoPersona, String password){

        // IR A TEMPAIL
        // Abrir nueva sesión del navegador y navegar
        WebDriver driver = new ChromeDriver();
        driver.get("https://tempail.com/");

        // Agregar tiempo para los elementos que no aparencen
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        /// Maximizar ventana
        driver.manage().window().maximize();

        // Obtener el correo
        WebElement elementCorreo = driver.findElement(By.xpath("//*[@id=\"eposta_adres\"]"));
        String correo = elementCorreo.getAttribute("data-clipboard-text");

        /// Sacar el correo sin @gufum.com
        // Identificar el indice de @
        int index = correo.indexOf("@");

        // Obtener subcorreo
        String subCorreo = correo.substring(0,index);

        // IR A GOOGLE
        // Cambiar a una nueva pestaña
        driver.switchTo().newWindow(WindowType.TAB);
        String urlGoogle = "https://accounts.google.com/v3/signin/identifier?continue=https%3A%2F%2Fwww.google.com%2Fsearch%3Fq%3Dgoogle%26rlz%3D1C1ONGR_enDO1105DO1105%26oq%3Dgoogle%26gs_lcrp%3DEgZjaHJvbWUyBggAEEUYOTIGCAEQRRg8MgYIAhBFGDzSAQgxOTU0ajBqNKgCALACAA%26sourceid%3Dchrome%26ie%3DUTF-8&ec=GAZAAQ&hl=es&ifkv=ARpgrqdbSCCU4XrCMxcfPXeCx7pcxct030sqY4i4-PyJB2k_7JrHjauYLp03ptlOCQJBYVNRGcymiA&passive=true&flowName=GlifWebSignIn&flowEntry=ServiceLogin&dsh=S-1291722785%3A1728671449768620&ddm=0";
        driver.get(urlGoogle);

        // Clikear boton crear cuenta
        WebElement btnCrearCuenta = driver.findElement(By.xpath("//*[@id=\"yDmH0d\"]/c-wiz/div/div[3]/div/div[2]/div/div/div[1]/div/button/span"));
        btnCrearCuenta.click();

        // Clikear boton personal
        WebElement btnPersonal = driver.findElement(By.xpath("//*[@id=\"yDmH0d\"]/c-wiz/div/div[3]/div/div[2]/div/div/div[2]/div/ul/li[1]/span[3]"));
        btnPersonal.click();

        // Nombre cuenta
        WebElement nombreC = driver.findElement(By.name("firstName"));
        nombreC.sendKeys(nombreCuenta);

        // Clickear boton siguiente
        WebElement btnSiguiente = driver.findElement(By.xpath("//*[@id=\"collectNameNext\"]/div/button/span"));
        btnSiguiente.click();

        // Introducir dia de nacimiento
        WebElement diaC = driver.findElement(By.id("day"));
        diaC.sendKeys(diaCuenta);

        // Tiempo de espera de 3 segundos
        tiempoEspera(3000);

        // Introducir mes de nacimiento
        WebElement mesC = driver.findElement(By.id("month"));
        mesC.click();

        List<WebElement> listMeses =driver.findElements(By.xpath("//*[@id=\"month\"]/option"));
        int indexListaMeses = 0;
        for (int e=0; e<listMeses.size(); e++){

            String mesParametro = mesCuenta.toLowerCase();
            String mesTraido = listMeses.get(e).getText().toLowerCase();

            if (mesTraido.equals(mesParametro)){
                indexListaMeses = e;
            }
        }
        listMeses.get(indexListaMeses).click();

        // Introducir año de nacimiento
        WebElement anioC = driver.findElement(By.id("year"));
        anioC.sendKeys(anioCuenta);

        // Introducir genero
        WebElement gneroC = driver.findElement(By.id("gender"));
        gneroC.click();

        List<WebElement> listGemero =driver.findElements(By.xpath("//*[@id=\"gender\"]/option"));
        int indexListaGemero = 0;
        for (int e = 0; e< listGemero.size(); e++){

            if(listGemero.get(e).getText().equals("Hombre") || listGemero.get(e).getText().equals("Mujer")){
                String generoParametro = generoPersona.toLowerCase();
                String generoTraido = listGemero.get(e).getText().toLowerCase();

                if (generoParametro.equals(generoTraido)){
                    indexListaGemero = e;
                }else {
                    indexListaGemero = e;
                }
            }
        }
        listGemero.get(indexListaGemero).click();


        // Clickear siguiente
        WebElement btnSiguiente3 = driver.findElement(By.xpath("//*[@id=\"birthdaygenderNext\"]/div/button/span"));
        btnSiguiente3.click();

        tiempoEspera(2000);

        try {
            // Clikear boton Crear dirección personalizada
            WebElement btnDireccionPersonalizada = driver.findElement(By.xpath("//*[@id=\"yDmH0d\"]/c-wiz/div/div[2]/div/div/div/form/span/section/div/div/div[1]/div[1]/div/span/div[3]/div/div[1]/div/div[3]/div"));
            btnDireccionPersonalizada.click();

        } catch (Exception e) {
            System.out.println("Hubo un problema con el radio buttom direccion personalizada: "+e);
        }

        // Pasar correo al input
        WebElement inputIntroducirCorreo = driver.findElement(By.name("Username"));
        inputIntroducirCorreo.sendKeys(subCorreo);

        // Clikear boton siguiente 4
        WebElement btnSiguiente4 = driver.findElement(By.xpath("//*[@id=\"next\"]/div/button/span"));
        btnSiguiente4.click();

        // Pasar contraseña al input
        WebElement inputIntroducirPassword = driver.findElement(By.name("Passwd"));
        inputIntroducirPassword.sendKeys(password);

        // Repetir contraseña
        WebElement inputIntroducirPassword2 = driver.findElement(By.name("PasswdAgain"));
        inputIntroducirPassword2.sendKeys(password);

        // Clikear boton siguiente 5
        WebElement btnSiguiente5 = driver.findElement(By.xpath("//*[@id=\"createpasswordNext\"]/div/button/span"));
        btnSiguiente5.click();

    }

}


