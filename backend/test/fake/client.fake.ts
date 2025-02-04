import { ClientDomain } from "@domain/client.domain";
import { Client } from "@domain/models/client.model";
import { faker } from "@faker-js/faker";

export const makeClientFake = (props?: Partial<Client>): ClientDomain => new ClientDomain({
    id: props.id || faker.number.int({ max: 100000 }),
    name: props.name || faker.lorem.words({ min: 1, max: 10 }),
    contact: props.contact ||  faker.lorem.words({ min: 1, max: 10 })
  })