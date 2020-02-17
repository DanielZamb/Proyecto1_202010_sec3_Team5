package model.data_structures;

public class Properties {

    private Integer OBJECTID;

    private String FECHA_HORA;

    private String MEDIO_DETE;

    private String CLASE_VEHI;

    private String TIPO_SERVI;

    private String INFRACCION;

    private String DES_INFRAC;

    private String LOCALIDAD;

    public Integer getOBJECTID() {
        return OBJECTID;
    }

    public void setOBJECTID(Integer oBJECTID) {
        this.OBJECTID = oBJECTID;
    }

    public String getFECHA_HORA() {
        return FECHA_HORA;
    }

    public void setFECHA_HORA(String fECHAHORA) {
        this.FECHA_HORA = fECHAHORA;
    }

    public String getMEDIO_DETE() {
        return MEDIO_DETE;
    }

    public void setMEDIO_DETE(String mEDIODETE) {
        this.MEDIO_DETE = mEDIODETE;
    }

    public String getCLASE_VEHI() {
        return CLASE_VEHI;
    }

    public void setCLASE_VEHI(String cLASEVEHI) {
        this.CLASE_VEHI = cLASEVEHI;
    }

    public String getTIPO_SERVI() {
        return TIPO_SERVI;
    }

    public void setTIPO_SERVI(String tIPOSERVI) {
        this.TIPO_SERVI = tIPOSERVI;
    }

    public String getINFRACCION() {
        return INFRACCION;
    }

    public void setINFRACCION(String iNFRACCION) {
        this.INFRACCION = iNFRACCION;
    }

    public String getDES_INFRAC() {
        return DES_INFRAC;
    }

    public void setDES_INFRAC(String dESINFRAC) {
        this.DES_INFRAC = dESINFRAC;
    }

    public String getLOCALIDAD() {
        return LOCALIDAD;
    }

    public void setLOCALIDAD(String lOCALIDAD) {
        this.LOCALIDAD = lOCALIDAD;
    }

    public String toString(){
        String str ="OBJECTID : "+ this.OBJECTID +",\n"+
                "FECHA_HORA : "+ this.FECHA_HORA +",\n"+
                "MEDIO_DETE : "+ this.MEDIO_DETE +",\n"+
                "CLASE_VEHI : "+ this.CLASE_VEHI +",\n"+
                "TIPO_SERV : "+ this.TIPO_SERVI +",\n"+
                "INFRACCION : "+ this.INFRACCION +",\n"+
                "DESC_INFRAC : "+ this.DES_INFRAC +",\n"+
                "LOCALIDAD : "+ this.LOCALIDAD +"\n";
        return str;
    }
}
