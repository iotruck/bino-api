package br.com.iotruck.bino.extensions;

import br.com.iotruck.bino.entity.Company;
import br.com.iotruck.bino.entity.Truck;
import br.com.iotruck.bino.entity.Trucker;
import br.com.iotruck.bino.entity.enuns.FuelType;
import br.com.iotruck.bino.entity.enuns.TruckType;
import br.com.iotruck.bino.repository.ITruckRepository;
import br.com.iotruck.bino.repository.ITruckerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.time.LocalDate;

public class ReadFile {

    @Autowired
    ITruckerRepository truckerRepository;

    @Autowired
    ITruckRepository truckRepository;

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
        String licensePlace;
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
                if (typeRegister.equals("00")) {

                } else if (typeRegister.equals("01")) {
                    System.out.println("\nTrailer");

                } else if (typeRegister.equals("02")) {

                    Truck truck = new Truck();
                    Company company = new Company();

                    id = Integer.parseInt(register.substring(2, 22));
                    truck.setId(id);
                    nameTruck = register.substring(22, 32).trim();
                    truck.setName(nameTruck);
                    truckBrand = register.substring(32, 46).trim();
                    truck.setTruckBrand(truckBrand);
                    licensePlace = register.substring(46, 57).trim();
                    truck.setLicensePlace(licensePlace);
                    truckType = register.substring(57, 71);
                    truck.setTruckType(TruckType.valueOf(truckType));
                    fuelType = register.substring(71,82);
                    truck.setFuelType(FuelType.valueOf(fuelType));
                    status = register.substring(90, 106);
                    truck.setStatus(status);
                    company.setId((Integer.parseInt(register.substring(86, 92))));
                    truck.setCompany(company);

                    truckRepository.save(truck);
                    contRegister++;

                } else if (typeRegister.equals("04")) {

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

                    //Utiliza no register dessa maneira para double, lembrar de usar parseDouble;
                    //.replace(',', '.')

                    truckerRepository.save(trucker);
                    contRegister++;

                }  else {
                    System.out.println("Tipo de registro inválido");
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
