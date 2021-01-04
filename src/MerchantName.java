import java.util.Objects;

public class MerchantName {
    private String merchantName;
    private Integer count;

    public MerchantName() {
    }

    public MerchantName(String merchantName, Integer count) {
        this.merchantName = merchantName;
        this.count = count;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
