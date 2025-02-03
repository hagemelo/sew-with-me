import { MaterialUsed } from "./models/material-used.model";
import { Material } from "./models/material.model";
import { Unit } from "./models/unit.model";

export class MaterialUsedDomain implements MaterialUsed {
   
    constructor(
        private readonly props: MaterialUsed,
    ) { }

    get id (): number { return this.props.id; }
    get quantity (): number { return this.props.quantity; }
    get unit (): Unit { return this.props.unit; }
    get material (): Material { return this.props.material; }

    toModel (): MaterialUsed {
        return this.props;
    }
}