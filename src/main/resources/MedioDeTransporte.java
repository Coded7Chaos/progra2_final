public class MedioDeTransporte {
    package proyecto;
import java.util.*;


    public abstract class MedioTransporte {
        private TipoTransporte medioDeTransporte;
        private List<Caracteristica> ventajas = new ArrayList<>();
        private List<Caracteristica> desventajas = new ArrayList<>();

        public MedioTransporte(TipoTransporte medioDeTransporte, List<Caracteristica> ventajas,
                               List<Caracteristica> desventajas) {
            super();
            this.medioDeTransporte = medioDeTransporte;
            this.ventajas = ventajas;
            this.desventajas = desventajas;
        }

        @Override
        public String toString() {
            return "MedioTransporte [medioDeTransporte=" + medioDeTransporte + ", ventajas=" + ventajas + ", desventajas="
                    + desventajas + "]";
        }

        public TipoTransporte getMedioDeTransporte() {
            return medioDeTransporte;
        }

        public void setMedioDeTransporte(TipoTransporte medioDeTransporte) {
            this.medioDeTransporte = medioDeTransporte;
        }

        public List<Caracteristica> getVentajas() {
            return ventajas;
        }

        public void setVentajas(List<Caracteristica> ventajas) {
            this.ventajas = ventajas;
        }

        public List<Caracteristica> getDesventajas() {
            return desventajas;
        }

        public void setDesventajas(List<Caracteristica> desventajas) {
            this.desventajas = desventajas;
        }

        public abstract double calcularTarifa();

    }

}
