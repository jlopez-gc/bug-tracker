import { StatusPayload } from './StatusPayload';
import { BugPayload } from './BugPayload';

export default class BugCreationRequestPayload {
    name: string;
    description: string;
    status: StatusPayload | null;

    constructor() {
        this.name = '';
        this.description = '';
        this.status = null;
    }

    static from(bug: BugPayload): BugCreationRequestPayload {
        const request: BugCreationRequestPayload = new BugCreationRequestPayload();
        request.name = bug.name;
        request.description = bug.description;
        request.status = bug.status;
        return request;
    }
}
