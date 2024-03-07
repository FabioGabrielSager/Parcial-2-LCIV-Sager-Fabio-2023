package ar.edu.utn.frc.tup.lc.iv.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CargoRequest {
    private Long cargoId;
    private String cargoNombre;
    private Long distritoId;
}
