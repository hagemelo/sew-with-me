import { MaterialDomain } from "@domain/material.domain";
import { Material } from "@domain/models/material.model";
import { Unit } from "@domain/models/unit.model";
import { faker } from "@faker-js/faker";

export const makeMaterialFake = (props?: Partial<Material>): MaterialDomain => new MaterialDomain({
    id: props.id || faker.number.int({ max: 100000 }),
    name: props.name || faker.lorem.words({ min: 1, max: 10 }),
    describe: props.describe || faker.lorem.words({ min: 1, max: 10 }),
    unit: props.unit || Unit.CX,
  })