import { Material } from "./material.model";
import { Sew } from "./sew.model";
import { Unit } from "./unit.model";

export interface MaterialUsed {
    id?: number;
	quantity: number;
	unit: Unit;
	material?: Material; 
	sew?: Sew;  
}