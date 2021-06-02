package br.com.iotruck.bino.controller;

import br.com.iotruck.bino.entity.Truck;
import br.com.iotruck.bino.entity.Trucker;
import br.com.iotruck.bino.recorder.ListaObj;
import br.com.iotruck.bino.repository.ITruckRepository;
import br.com.iotruck.bino.repository.ITruckerRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RequestMapping("/download")
@CrossOrigin(origins = "*")
@AllArgsConstructor
@RestController
@Api("Bino API REST Security Analyst")
public class DownloadController {
    
    final ITruckerRepository repository;

    final ITruckRepository repositoryTruck;

    @GetMapping(value = "/iotruckmotoristascsv", produces = "application/csv")
    @ApiOperation("Realiza o download do csv")
    @ResponseBody
    public ResponseEntity getFileCsv() {

        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Disposition", "attachment; filename=iotruckCaminhoneiro.csv");

        List<Trucker> truckers = repository.findAll();
        if (truckers.isEmpty()) {
            return ResponseEntity.status(401).build();
        }
        ListaObj<Trucker> truckersObj = new ListaObj<>(truckers.size());

        for (int i = 0; i < truckers.size(); i++) {
            truckersObj.adicionar(truckers.get(i));
        }

        String body = "";

        for (int i = 0; i < truckersObj.getNmrElemento(); i++) {
            Trucker t = truckersObj.getElemento(i);
            body += String.format("%d;%s;%s;%s;%s;%s;%s%n\n", t.getId(), t.getName(),
                    t.getBirthDate(), t.getCpf(), t.getCnh(), t.getPhoneNumber(), t.getCertification());
        }

        return ResponseEntity.status(200).headers(headers).body(body);
    }

    @GetMapping(value = "/iotruckCaminhoesTXT", produces = "application/txt")
    @ApiOperation("Realiza o download do txt")
    @ResponseBody
    public ResponseEntity getFileTxt() {

        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Disposition", "attachment; filename=iotruckCaminhoes.txt");
        List<Truck> trucks = repositoryTruck.findAll();
        if (trucks.isEmpty()) {
            return ResponseEntity.status(401).build();
        }
        ListaObj<Truck> truckObj = new ListaObj<>(trucks.size());

        for (int i = 0; i < trucks.size(); i++) {
            truckObj.adicionar(trucks.get(i));
        }

        String header = "";
        String corpo = "";
        String body = "";
        String trailer = "";
        int contRegDados = 0;

        Date dataDeHoje = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        header += "00CAMINHAO";
        header += formatter.format(dataDeHoje);
        header += "01\n";

        corpo += "02";

        for (int i = 0; i < truckObj.getNmrElemento(); i++) {
            Truck t = truckObj.getElemento(i);
            corpo = String.format("%03d", t.getId());
            corpo += String.format("%7s", t.getName());
            corpo += String.format("%4s", t.getTruckBrand());
            corpo += String.format("%13s", t.getLicensePlate());
            corpo += String.format("%9s", t.getTruckType());
            corpo += String.format("%8s", t.getFuelType());
            corpo += String.format("%20s", t.getStatus());
            body += corpo + "\n";
            contRegDados++;
        }

        trailer += "01";
        trailer += String.format("%010d", contRegDados);

        return ResponseEntity.status(200).headers(headers).body(header + body + trailer);
    }
}


