import { Material } from "./material.model";
import { Unit } from "./unit.model";

export interface MaterialUsed {
    id?: number;
	quantity: number;
	unit: Unit;
	material: Material;   
}