package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.BasePositionalCommand;
import io.graversen.minecraft.rcon.util.Position;

import java.util.Objects;

public class PlaceCommand extends BasePositionalCommand {
    private final PlaceType placeType;
    private final String resource;
    private final String[] additionalArguments;

    enum PlaceType {
        FEATURE,
        JIGSAW,
        STRUCTURE,
        TEMPLATE
    }

    public enum TemplateRotation {
        NONE,
        CLOCKWISE_90,
        R_180,
        COUNTERCLOCKWISE_90;

        public String toRotationName() {
            return name().replace("R_", "").toLowerCase();
        }
    }

    public enum TemplateMirror {
        NONE,
        LEFT_RIGHT,
        FRONT_BACK;

        public String toMirrorString() {
            return name().toLowerCase();
        }
    }

    private PlaceCommand(PlaceType placeType, String resource, Position position, String... additionalArguments) {
        super(position);
        this.placeType = Objects.requireNonNull(placeType);
        this.resource = Objects.requireNonNull(resource);
        this.additionalArguments = Objects.requireNonNullElse(additionalArguments, new String[]{});
    }

    public static PlaceCommand feature(String feature, Position position) {
        return new PlaceCommand(PlaceType.FEATURE, feature, Objects.requireNonNullElse(position, Position.relative()));
    }

    public static PlaceCommand jigsaw(String pool, String target, int maxDepth, Position position) {
        return new PlaceCommand(
                PlaceType.JIGSAW, "", Objects.requireNonNullElse(position, Position.relative()),
                Objects.requireNonNull(pool), Objects.requireNonNull(target),
                String.valueOf(maxDepth)
        );
    }

    public static PlaceCommand structure(String structure, Position position) {
        return new PlaceCommand(PlaceType.STRUCTURE, structure, Objects.requireNonNullElse(position, Position.relative()));
    }

    public static PlaceCommand template(String template, Position position, TemplateRotation rotation, TemplateMirror mirror, float integrity, int seed) {
        return new PlaceCommand(
                PlaceType.TEMPLATE, template, Objects.requireNonNullElse(position, Position.relative()),
                Objects.requireNonNullElse(rotation, TemplateRotation.NONE).toRotationName(),
                Objects.requireNonNullElse(mirror, TemplateMirror.NONE).toMirrorString(),
                String.valueOf(integrity), String.valueOf(seed)
        );
    }

    @Override
    public String command() {
        return ("structure " + switch (placeType) {
            case FEATURE -> "feature " + resource + " " + getPosition();
            case JIGSAW -> "jigsaw " + String.join(" ", additionalArguments) + " " + getPosition();
            case STRUCTURE -> "structure " + resource + " " + getPosition();
            case TEMPLATE -> "template " + resource + " " + getPosition() + " " + String.join(" ", additionalArguments);
        }).trim();
    }
}
