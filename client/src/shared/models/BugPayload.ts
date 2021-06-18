import { StatusPayload } from './StatusPayload';

export interface BugPayload {
    id: number | null;
    name: string;
    description: string;
    status: StatusPayload | null;
    createdAt: string;
    updatedAt: string;
}
