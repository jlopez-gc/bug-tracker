import React from 'react';
import { render } from '@testing-library/react';
import App from './App';
import { setupServer } from 'msw/node';
import { rest } from 'msw';

const server = setupServer(
    rest.get('*/bugs', (req, res, ctx) => {
        return res(
            ctx.status(200),
            ctx.json([
                {
                    id: 524,
                    name: 'Test Bug 1',
                    description: 'This is a simple bug description',
                    status: {
                        id: 1,
                        name: 'PENDING',
                    },
                    createdAt: '2020-12-27T22:00:00',
                    updatedAt: '2020-12-27T22:00:00',
                },
                {
                    id: 525,
                    name: 'Test Bug 2',
                    description: 'This is a simple bug description',
                    status: {
                        id: 1,
                        name: 'IN PROGRESS',
                    },
                    createdAt: '2020-12-27T22:00:00',
                    updatedAt: '2020-12-27T22:00:00',
                },
            ]),
        );
    }),
);

beforeAll(() => server.listen());
afterEach(() => server.resetHandlers());
afterAll(() => server.close());

test('renders the bug id, name and status when found', async () => {
    const { findByText } = await render(<App />);
    const idElement = await findByText(/524/i);
    expect(idElement).toBeInTheDocument();
    const nameElement = await findByText(/Test BugPayload 1/i);
    expect(nameElement).toBeInTheDocument();
    const statusElement = await findByText(/PENDING/i);
    expect(statusElement).toBeInTheDocument();
});

test('renders error message on error', async () => {
    server.use(
        rest.get('*/bugs', (req, res, ctx) => {
            return res(ctx.status(500));
        }),
    );
    const { findByText } = render(<App />);
    const linkElement = await findByText(/An error occurred, please try again later/i);
    expect(linkElement).toBeInTheDocument();
});
