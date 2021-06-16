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
                    id: 1,
                    name: 'Test Bug 1',
                    description: 'This is a simple bug description',
                },
            ]),
        );
    }),
);

beforeAll(() => server.listen());
afterEach(() => server.resetHandlers());
afterAll(() => server.close());

test('renders the bug name', async () => {
    const { findByText } = render(<App />);
    const linkElement = await findByText(/Test Bug 1/i);
    expect(linkElement).toBeInTheDocument();
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
