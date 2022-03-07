
# KATA - Point Of Interest - Search Engine 

This project is a challenge that a company gave me to demonstrate my skills.

For privacy reasons (and because i don't want other candidates reuse my code), i won't give the subject here.

Nevertheless, i will give the result here publicly. Enjoy !



## Point of interest file (poi.csv)
```text
@id @lat @lon
id1 -48.6 -37.7
id2 -27.1 8.4
id3 6.6 -6.9
id4 -2.3 38.3
id5 6.8 -6.9
id6 -2.5 38.3
id7 0.1 -0.1
id8 -2.1 38.1
```
## Quickstart Examples
```java
import java.io.IOException;
import fr.alten.amartin.kata_poi.controller.PoiSearchEngine;
import fr.alten.amartin.kata_poi.exceptions.IllegalFormatLineException;

public static void main( String[] args )
    {
    	try {
			PoiSearchEngine pse = new PoiSearchEngine("src/test/resources/poi.csv");
			String result = pse.findNstDensestAreasToJson(2);
			System.out.println(result);
		} catch (IllegalFormatLineException | IOException e) {
			e.printStackTrace();
		}
    }
```


## Output Result


```bash
[{"minLat":-2.5,"maxLat":-2.0,"minLon":38.0,"maxLon":38.5},{"minLat":6.5,"maxLat":7.0,"minLon":-7.0,"maxLon":-6.5}]

```
    