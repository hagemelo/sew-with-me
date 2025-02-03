import { Unit } from "./unit.model";

export interface Material {
    id?: number;
    name?: string;
    describe?: string;
    unit?: Unit;
}