
public class Individual {
    private byte[] chromozom = new byte[64];
    private byte[] kopiachromozom = new byte[64];
    private int pokladyfitnes;
    private int instrukciefitnes;
    private String posun;
    private int generacia;
    /**
     * vytvorenie jedinca kazdy jedinec ma svoj gen kopiu genou pre virtual machine aby jedinec mal svoj povodny gen a aj gen po prechode virtual machine jedinec ma informaciu aj v kt. generacii
     * vznikol. posuny po mapke a medoty na vypis genou
     * @return
     */

    public byte[] getchromozom() {
        return chromozom;
    }
    public void setchromozom(byte[] genn) {
        this.chromozom = genn;
    }
    public int getfitnespoklady() {
        return pokladyfitnes;
    }
    public void setfitnespoklady(int fitness) {
        this.pokladyfitnes = fitness;
    }
    public void setfitnesinstrukcie(int fitness) {
        this.instrukciefitnes = fitness;
    }
    public int getfitnesinstrukcia() {
        return instrukciefitnes;
    }
    public String getposun() {
        return posun;
    }
    public void setposun(String posunn) {
        this.posun = posunn;
    }
    public int getgeneracia() {
        return generacia;
    }
    public void setgeneracia(int gener) {
        this.generacia = gener;
    }
    public void setkopiachrom(byte[] genn){
        this.kopiachromozom = genn;
    }
    public byte[] getkopiachrom() {
        return kopiachromozom;
    }


    public void getprogram(){
        for(int i=0;i<64;i++){
            System.out.print(Integer.toBinaryString(chromozom[i]& 0xFF)+",");
        }
        System.out.println();
    }
    public void getkopiaprogram(){
        for(int i=0;i<64;i++){
            System.out.print(Integer.toBinaryString(kopiachromozom[i]& 0xFF)+",");
        }
        System.out.println();
    }
}
