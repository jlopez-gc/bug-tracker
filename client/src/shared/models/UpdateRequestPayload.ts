import { StatusPayload } from './StatusPayload';

export interface UpdateRequestPayload {
    id: number;
    name: string;
    description: string;
    status: StatusPayload;
}
