import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

interface CryptoAsset {
    String getSymbol();
    double getPrice();
    void trade(String side, double amount); // "buy" or "sell"
}

interface Mineable {
    void verifyOnChain();
    int getMiningDifficulty();
}

interface SmartContractCapable {
    void deployContract(String contractName);
    double estimateGas(String action);
}

interface Pegged {
    boolean isPegHealthy();
    double getReserveBacking();
}

interface NFTFeature {
    String getTokenId();
    String getMetadata();
    List<String> getOwnershipHistory();
    void transferOwnership(String newOwner);
}

class Bitcoin implements CryptoAsset, Mineable {
    private String symbol = "BTC";
    private double price;
    private int difficulty;

    Bitcoin(double price, int difficulty) {
        this.price = price;
        this.difficulty = difficulty;
    }

    public String getSymbol() { return symbol; }
    public double getPrice() { return price; }

    public void trade(String side, double amount) {
        System.out.println(side.toUpperCase() + " " + amount + " " + symbol + " at ₹" + (amount * price));
        verifyOnChain();
    }

    public void verifyOnChain() {
        System.out.println("Performing blockchain verification for BTC. Difficulty: " + difficulty);
    }

    public int getMiningDifficulty() { return difficulty; }
}

class Ethereum implements CryptoAsset, SmartContractCapable {
    private String symbol = "ETH";
    private double price;
    private double baseGas;

    Ethereum(double price, double baseGas) {
        this.price = price;
        this.baseGas = baseGas;
    }

    public String getSymbol() { return symbol; }
    public double getPrice() { return price; }

    public void trade(String side, double amount) {
        double fee = estimateGas("transfer") * baseGas;
        System.out.println(side.toUpperCase() + " " + amount + " " + symbol + " at ₹" + (amount * price));
        System.out.println("Estimated gas fee: ₹" + fee);
    }

    public void deployContract(String contractName) {
        System.out.println("Deploying contract '" + contractName + "' on Ethereum. Gas estimate: ₹" + estimateGas("deploy"));
    }

    public double estimateGas(String action) {
        switch (action.toLowerCase()) {
            case "deploy": return 1500;
            case "transfer": return 50;
            default: return 100;
        }
    }
}

class StableCoin implements CryptoAsset, Pegged {
    private String symbol;
    private double price;
    private double reserveBacking;

    StableCoin(String symbol, double price, double reserveBacking) {
        this.symbol = symbol;
        this.price = price;
        this.reserveBacking = reserveBacking;
    }

    public String getSymbol() { return symbol; }
    public double getPrice() { return price; }

    public void trade(String side, double amount) {
        System.out.println(side.toUpperCase() + " " + amount + " " + symbol + " at ₹" + (amount * price));
        System.out.println("Peg status: " + (isPegHealthy() ? "Healthy" : "Warning - peg deviation"));
    }

    public boolean isPegHealthy() {
        return reserveBacking >= price * 0.95;
    }

    public double getReserveBacking() { return reserveBacking; }
}

class NFT implements CryptoAsset, NFTFeature {
    private String symbol = "NFT";
    private double price;
    private String tokenId;
    private String metadata;
    private List<String> ownershipHistory = new ArrayList<>();

    NFT(String tokenId, String metadata, double initialPrice, String originalOwner) {
        this.tokenId = tokenId;
        this.metadata = metadata;
        this.price = initialPrice;
        ownershipHistory.add(originalOwner);
    }

    public String getSymbol() { return symbol + "-" + tokenId; }
    public double getPrice() { return price; }

    public void trade(String side, double amount) {
        if (!side.equalsIgnoreCase("transfer") && !side.equalsIgnoreCase("sell")) {
            System.out.println("NFT trading uses 'transfer' or 'sell' semantics.");
            return;
        }
        System.out.println(side.toUpperCase() + " NFT " + tokenId + " at ₹" + price + " (amount param ignored for single-token asset)");
        if (side.equalsIgnoreCase("sell")) {
            System.out.println("Marketplace fee applied: ₹" + (price * 0.05));
        }
    }

    public String getTokenId() { return tokenId; }
    public String getMetadata() { return metadata; }
    public List<String> getOwnershipHistory() { return ownershipHistory; }

    public void transferOwnership(String newOwner) {
        ownershipHistory.add(newOwner);
        System.out.println("Transferred NFT " + tokenId + " to " + newOwner);
    }
}

class TradingEngine {
    public void placeOrder(CryptoAsset asset, String side, double amount) {
        System.out.println("Placing market order for " + asset.getSymbol());
        asset.trade(side, amount);
        postTradeChecks(asset);
        System.out.println();
    }

    public void placeOrder(CryptoAsset asset, String side, double amount, double limitPrice) {
        System.out.println("Placing limit order for " + asset.getSymbol() + " at limit ₹" + limitPrice);
        if ((side.equalsIgnoreCase("buy") && limitPrice >= asset.getPrice()) ||
            (side.equalsIgnoreCase("sell") && limitPrice <= asset.getPrice())) {
            asset.trade(side, amount);
        } else {
            System.out.println("Limit price not favorable. Order queued.");
        }
        postTradeChecks(asset);
        System.out.println();
    }

    private void postTradeChecks(CryptoAsset asset) {
        if (asset instanceof Mineable) {
            Mineable m = (Mineable) asset;
            System.out.println("Run mineable-specific health check. Difficulty: " + m.getMiningDifficulty());
        }
        if (asset instanceof SmartContractCapable) {
            SmartContractCapable s = (SmartContractCapable) asset;
            System.out.println("Smart-contract platform gas preview for a sample action: ₹" + s.estimateGas("transfer"));
        }
        if (asset instanceof Pegged) {
            Pegged p = (Pegged) asset;
            System.out.println("Peg reserve backing: ₹" + p.getReserveBacking() + " | Healthy: " + p.isPegHealthy());
        }
        if (asset instanceof NFTFeature) {
            NFTFeature n = (NFTFeature) asset;
            System.out.println("NFT token " + n.getTokenId() + " ownership trail: " + n.getOwnershipHistory());
        }
    }
}

public class CryptoExchange {
    public static void main(String[] args) {
        CryptoAsset btc = new Bitcoin(4500000, 248000000);
        CryptoAsset eth = new Ethereum(200000, 120);
        CryptoAsset usdt = new StableCoin("USDT", 83.5, 85.0);
        NFT rareArt = new NFT(UUID.randomUUID().toString().substring(0,6), "Rare digital painting by Mira", 250000, "alice");

        TradingEngine engine = new TradingEngine();

        engine.placeOrder(btc, "buy", 0.01);
        engine.placeOrder(eth, "sell", 1.5, 190000); 
        engine.placeOrder(usdt, "buy", 1000);
        engine.placeOrder(rareArt, "sell", 1);

        if (rareArt instanceof NFTFeature) {
            NFTFeature nf = rareArt;
            nf.transferOwnership("bob");
            System.out.println();
        }

        engine.placeOrder(rareArt, "transfer", 0); 
    }
}
