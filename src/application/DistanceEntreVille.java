package application;


import org.json.simple.JSONArray;

public class DistanceEntreVille {
    public static void main(String[] args) {
    	
    	JSONObject j = new JsonObject();
    	
    	
        // Remplacez YOUR_API_KEY par votre propre cl� API Google Maps
        GeoApiContext context = new GeoApiContext.Builder()
            .apiKey("YOUR_API_KEY")
            .build();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Entrez la premi�re ville :");
        String ville1 = scanner.nextLine();

        System.out.println("Entrez la deuxi�me ville :");
        String ville2 = scanner.nextLine();

        try {
            // Envoie une requ�te � l'API pour obtenir la distance � vol d'oiseau entre les deux villes
            DistanceMatrixApiRequest request = DistanceMatrixApi.newRequest(context)
                .origins(ville1)
                .destinations(ville2)
                .mode(TravelMode.DRIVING)
                .avoid(DirectionsApi.RouteRestriction.TOLLS);

            DistanceMatrix result = request.await();

            // R�cup�re la distance � vol d'oiseau en m�tres
            long distance = result.rows[0].elements[0].distance.inMeters;

            System.out.println("La distance entre " + ville1 + " et " + ville2 + " est de " + distance + " m�tres.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
