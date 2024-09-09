package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.moeder;


import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.Type;

public record BevallingJSON(BevallingToestandJSON bevallingToestand) implements Type {
    @Override
    public String type() {
        return "Bevalling";
    }

}
