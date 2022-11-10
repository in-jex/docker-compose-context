package injex.dcc.model;

public class ComplexServiceName {
    private final String mainName;
    private String service;

    public ComplexServiceName(String serviceName) {
        if (serviceName.contains(":")) {
            String[] nameChain = serviceName.split(":");
            if (nameChain.length > 2) {
                throw new UnsupportedOperationException("Please use [] to specify multiple services");
            }
            mainName = nameChain[0];
            if (nameChain[1].length() > 0) {
                service = nameChain[1];
            }
        } else if (serviceName.contains("[") && serviceName.contains("]")) {
            throw new UnsupportedOperationException("Not implemented yet");
        } else {
            mainName = serviceName;
        }
    }

    public String getMainName() {
        return mainName;
    }

    public boolean hasService() {
        return service != null;
    }

    public String getService() {
        return service;
    }
}
