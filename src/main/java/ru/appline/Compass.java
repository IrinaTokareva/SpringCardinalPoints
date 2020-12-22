package ru.appline;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
public class Compass {
    private CardinalPoints cardinalPoints = CardinalPoints.getInstance();

    /*
    Request body example:
    {
        "North": {"left": 338, "right": 22},
        "North-East": {"left": 23, "right": 67},
        "East":  {"left": 68, "right": 112},
        "East-South":  {"left": 113, "right": 157},
        "South": {"left": 158, "right": 202},
        "South-West": {"left": 203, "right": 247},
        "West": {"left": 248, "right": 292},
        "West-North": {"left": 293, "right": 337}
    }
    */
    @PostMapping(value = "/define", consumes = "application/json", produces = "application/json")
    public String define(@RequestBody String body) {
        try {
            JSONObject jsonBody = new JSONObject(body);
            for (String key : jsonBody.keySet()) {
                JSONObject values = jsonBody.getJSONObject(key);
                cardinalPoints.addPoint(key, values.getInt("left"), values.getInt("right"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new JSONObject().put("error", e.getMessage()).toString();
        }
        System.out.println(cardinalPoints.getMap());
        return new JSONObject().put("info", "Cardinal points are defined")
                .put("points", new JSONObject(cardinalPoints.getMap()))
                .toString();
    }

    /*
    Request body example:
    {
	    "degree": 400
    }
     */
    @GetMapping(value = "/getSide", consumes = "application/json", produces = "application/json")
    public String getCardinalPoint(@RequestBody String body) {
        try {
            return new JSONObject()
                    .put("side", cardinalPoints.getPointName(
                            new JSONObject(body).getInt("degree")))
                    .toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new JSONObject().put("error", e.getMessage()).toString();
        }
    }
}
