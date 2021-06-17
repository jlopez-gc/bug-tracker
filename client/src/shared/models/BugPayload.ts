import { StatusPayload } from './StatusPayload';

export interface BugPayload {
    id: number;
    name: string;
    description: string;
    status: StatusPayload;
    createdAt: string;
    updatedAt: string;
}
