import { MaterialUsedDomain } from "@domain/material-used.domain";
import { MaterialUsed } from "@domain/models/material-used.model";
import { Unit } from "@domain/models/unit.model";
import { faker } from "@faker-js/faker";
import { makeMaterialFake } from "./material.fake";

export const makeMaterialUsedFake = (props?: Partial<MaterialUsed>): MaterialUsedDomain => new MaterialUsedDomain({
    id: props.id || faker.number.int({ max: 100000 }),
    quantity: props.quantity || faker.number.int({ max: 100 }),
    unit: props.unit || Unit.CX,
    material: props.material || makeMaterialFake()
  })