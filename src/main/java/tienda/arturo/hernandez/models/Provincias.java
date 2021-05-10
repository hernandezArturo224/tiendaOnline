
package tienda.arturo.hernandez.models;


public class Provincias implements Comparable<Provincias>{

    private String id;
    private String nm;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNm() {
        return nm;
    }

    public void setNm(String nm) {
        this.nm = nm;
    }

	@Override
	public int compareTo(Provincias o) {
		if(this.nm.toLowerCase().compareTo(o.nm.toLowerCase()) < 0) {
			return -1;
		}else {
			return 1;
		}
	}

}
