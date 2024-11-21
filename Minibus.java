public class Minibus {
    package proyecto;

import java.util.*;

    public class Minibus extends MedioTransporte{

        private int numeroRuta; //288
        private String color; // el color del cartelito principal

        public Minibus(TipoTransporte medioDeTransporte, List<Caracteristica> ventajas, List<Caracteristica> desventajas, int numeroRuta,String color) {
            super(medioDeTransporte, ventajas, desventajas);
            this.numeroRuta = numeroRuta ;
            this.color = color ;
        }

        public List<String> cartelitos(Parada ubicacion){
            List<String> cartelitos = new ArrayList<>();
            return cartelitos;
        }

        @Override
        public double calcularTarifa() {
            // TODO Auto-generated method stub
            return 0;
        }



    }

}
