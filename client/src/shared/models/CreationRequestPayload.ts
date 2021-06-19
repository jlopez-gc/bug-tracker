import { StatusPayload } from './StatusPayload';

interface CreationRequestPayload {
    name: string;
    description: string;
    status: StatusPayload;
}

export default CreationRequestPayload;
