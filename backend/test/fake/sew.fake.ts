import { MaterialUsedDomain } from "@domain/material-used.domain";
import { MaterialUsed } from "@domain/models/material-used.model";
import { Unit } from "@domain/models/unit.model";
import { faker } from "@faker-js/faker";
import { makeMaterialFake } from "./material.fake";
import { SewDomain } from "@domain/sew.domain";
import { Sew } from "@domain/models/sew.model";
import { TypeSew } from "@domain/models/type-sew.model";
import { SewStatus } from "@domain/models/sew-status.model";
import { makeMaterialUsedFake } from "./material-used.fake";

export const makeSewFake = (props?: Partial<Sew>): SewDomain => new SewDomain({
    id: props.id || faker.number.int({ max: 100000 }),
    type: props.type || TypeSew.REPARO,
	status: props.status || SewStatus.NAO_INICIADO,
    describe: props.describe || faker.lorem.words({ min: 1, max: 10 }),
    deliveryForecast: props.deliveryForecast || faker.date.soon(),
    value: props.value || faker.number.int({ max: 100000 }),
	materials: props.materials || [makeMaterialUsedFake()],
  })