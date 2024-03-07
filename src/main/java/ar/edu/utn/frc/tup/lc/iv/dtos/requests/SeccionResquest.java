package ar.edu.utn.frc.tup.lc.iv.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeccionResquest {
    private Long seccionId;
    private String seccionNombre;
    private Long distritoId;
}
