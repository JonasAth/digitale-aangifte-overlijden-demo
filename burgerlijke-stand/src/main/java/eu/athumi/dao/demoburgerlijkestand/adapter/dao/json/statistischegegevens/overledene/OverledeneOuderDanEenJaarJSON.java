package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.overledene;


import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.Geslacht;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.NationaliteitJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.burgerlijkeStaat.BurgerlijkeStaatJSONType;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.burgerlijkeStaat.HuwelijkJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.geboorte.GeboorteJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.locatie.AdresJSON;

public record OverledeneOuderDanEenJaarJSON(
        Geslacht geslacht,
        NationaliteitJSON nationaliteit,
        BurgerlijkeStaatJSONType burgerlijkeStaat,
        HuwelijkJSON huwelijk,
        GeboorteJSON geboorte,
        AdresJSON verblijfplaats
) implements OverledeneJSON {

    @Override
    public Geslacht geslacht() {
        return geslacht;
    }

}
