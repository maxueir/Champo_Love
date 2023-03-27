package application;


import org.json.simple.JSONArray;

public class DistanceEntreVille {
    public static void main(String[] args) {
    	
    	JSONObject j = new JsonObject();
    	
    	
        // Remplacez YOUR_API_KEY par votre propre clé API Google Maps
        GeoApiContext context = new GeoApiContext.Builder()
            .apiKey("YOUR_API_KEY")
            .build();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Entrez la première ville :");
        String ville1 = scanner.nextLine();

        System.out.println("Entrez la deuxième ville :");
        String ville2 = scanner.nextLine();

        try {
            // Envoie une requête à l'API pour obtenir la distance à vol d'oiseau entre les deux villes
            DistanceMatrixApiRequest request = DistanceMatrixApi.newRequest(context)
                .origins(ville1)
                .destinations(ville2)
                .mode(TravelMode.DRIVING)
                .avoid(DirectionsApi.RouteRestriction.TOLLS);

            DistanceMatrix result = request.await();

            // Récupère la distance à vol d'oiseau en mètres
            long distance = result.rows[0].elements[0].distance.inMeters;

            System.out.println("La distance entre " + ville1 + " et " + ville2 + " est de " + distance + " mètres.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
