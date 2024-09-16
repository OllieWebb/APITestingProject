package pojo;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Order{

    @JsonProperty("petId")
    private int petId;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("id")
    private long id;

    @JsonProperty("shipDate")
    private String shipDate;

    @JsonProperty("complete")
    private boolean complete;

    @JsonProperty("status")
    private String status;

    public Order() {
    }

    public int getPetId(){
        return petId;
    }

    public int getQuantity(){
        return quantity;
    }

    public long getId(){
        return id;
    }

    public String getShipDate(){
        return shipDate;
    }

    public boolean isComplete(){
        return complete;
    }

    public String getStatus(){
        return status;
    }
}