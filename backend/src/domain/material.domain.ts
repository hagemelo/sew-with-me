import { Material } from "./models/material.model";
import { Unit } from "./models/unit.model";

export class MaterialDomain implements Material {

    constructor(
        private readonly props: Material,
    ) { }

    get id (): number { return this.props.id; }
    get name (): string { return this.props.name; }
    get describe (): string { return this.props.describe; }
    get unit (): Unit { return this.props.unit; }

    toModel (): Material {
        return this.props;
    }
}