import { StatusPayload } from './StatusPayload';
import { BugPayload } from './BugPayload';

export default class BugUpdateRequestPayload {
    id: number | null;
    name: string;
    description: string;
    status: StatusPayload | null;

    constructor() {
        this.id = null;
        this.name = '';
        this.description = '';
        this.status = null;
    }

    public static from(bug: BugPayload): BugUpdateRequestPayload {
        const request: BugUpdateRequestPayload = new BugUpdateRequestPayload();
        request.id = bug.id;
        request.name = bug.name;
        request.description = bug.description;
        request.status = bug.status;
        return request;
    }
}
