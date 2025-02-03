import { Client } from "./models/client.model";

export class ClientDomain implements Client {

    constructor(
        private readonly props: Client,
    ) { }

    get id (): number { return this.props.id; }
    get name (): string { return this.props.name; }
    get contact (): string { return this.props.contact; }

    toModel (): Client {
        return this.props;
    }

}