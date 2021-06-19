import { StatusPayload } from './StatusPayload';

export class BugPayload {
    id: number | null;
    name: string;
    description: string;
    status: StatusPayload | null;
    createdAt: string;
    updatedAt: string;

    constructor() {
        this.id = null;
        this.name = '';
        this.description = '';
        this.status = null;
        this.createdAt = 'null';
        this.updatedAt = '';
    }

    static fromServerData(serverData: BugPayload): BugPayload {
        const newBug = new BugPayload();
        newBug.id = serverData.id;
        newBug.name = serverData.name;
        newBug.description = serverData.description;
        newBug.status = serverData.status;
        newBug.createdAt = serverData.createdAt;
        newBug.updatedAt = serverData.updatedAt;
        return newBug;
    }
}
