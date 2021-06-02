package br.com.iotruck.bino.extensions;

import br.com.iotruck.bino.entity.Company;
import br.com.iotruck.bino.entity.Truck;
import br.com.iotruck.bino.entity.Trucker;
import br.com.iotruck.bino.entity.enuns.FuelType;
import br.com.iotruck.bino.entity.enuns.TruckStatus;
import br.com.iotruck.bino.entity.enuns.TruckType;
import br.com.iotruck.bino.repository.ITruckRepository;
import br.com.iotruck.bino.repository.ITruckerRepository;
import br.com.iotruck.bino.services.TruckServices;
import br.com.iotruck.bino.services.TruckerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.time.LocalDate;
import java.util.Locale;


public class ReadFile {



    TruckerService truckerServices;

    TruckServices truckServices;

    public ReadFile(TruckerService truckerServices, TruckServices truckServices) {
        this.truckerServices = truckerServices;
        this.truckServices = truckServices;
    }

    public static File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    public void reader(MultipartFile file) {
        BufferedReader input = null;
        String register;
        String typeRegister;
        String name;
        String birthDate;
        String cpf;
        String cnh;
        String phoneNumber;
        String certification;

        Integer id;
        String nameTruck;
        String truckBrand;
        String licensePlate;
        String truckType;
        String fuelType;
        String status;

        int contRegister = 0;

        try {
            input = new BufferedReader(new FileReader(convert(file)));
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }

        try {
            register = input.readLine();

            while (register != null) {
                typeRegister = register.substring(0, 2);
                switch (typeRegister) {
                    case "00":
                        System.out.println("\nHeader");

                        break;
                    case "01":
                        System.out.println("\nTrailer");

                        break;
                    case "02": {

                        Truck truck = new Truck();
                        Company company = new Company();

                        nameTruck = register.substring(2, 22).trim();
                        truck.setName(nameTruck);
                        truckBrand = register.substring(22, 36).trim();
                        truck.setTruckBrand(truckBrand);
                        licensePlate = register.substring(36, 47).trim();
                        truck.setLicensePlate(licensePlate);
                        truckType = register.substring(47, 61).toUpperCase(Locale.ROOT).trim();
                        truck.setTruckType(TruckType.valueOf(truckType));
                        fuelType = register.substring(61, 72).toUpperCase(Locale.ROOT).trim();
                        truck.setFuelType(FuelType.valueOf(fuelType));
                        status = register.substring(72, 88).toUpperCase(Locale.ROOT).trim();
                        truck.setStatus(TruckStatus.valueOf(status));
                        company.setId((Integer.parseInt(register.substring(88, 94))));
                        truck.setCompany(company);
//                        0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789
//                        02NOMECAMINHOTESTETESTMercedesDXXXXXXIOTRU-4276BUCKET        S500       FREE            000001



                        truckServices.create(truck);
                        contRegister++;

                        break;
                    }
                    case "04": {

                        Trucker trucker = new Trucker();
                        Company company = new Company();

                        name = register.substring(2, 30).trim();
                        trucker.setName(name);
                        birthDate = register.substring(30, 40).trim();
                        trucker.setBirthDate(LocalDate.parse(birthDate));
                        cpf = register.substring(40, 54).trim();
                        trucker.setCpf(cpf);
                        cnh = register.substring(54, 65).trim();
                        trucker.setCnh(cnh);
                        phoneNumber = register.substring(65, 79).trim();
                        trucker.setPhoneNumber(phoneNumber);
                        certification = register.substring(79, 94).trim();
                        trucker.setCertification(certification);
                        company.setId((Integer.parseInt(register.substring(94, 100))));
                        trucker.setCompany(company);
//                        0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789
//                        04NOMENOMENOMENOMENOMENOMENOME1995-02-24499.914.138-8046328920451(11)95030-4166certificationXD000001

                        //Utiliza no register dessa maneira para double, lembrar de usar parseDouble;
                        //.replace(',', '.')

                        truckerServices.create(trucker);
                        contRegister++;

                        break;
                    }
                    default:
                        System.out.println("Tipo de registro inválido");
                        break;
                }

                register = input.readLine();
            }

            input.close();
        } catch (IOException e) {
            System.err.printf("Erro ao ler arquivo: %s.\n", e.getMessage());
        }

    }

//    public static void main(String[] args) {
//        String nomeArq = "ArquivoNotas.txt";
//        leArquivo(nomeArq);
//    }

}
