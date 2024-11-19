// app/page.tsx

"use client";
import Link from "next/link";
import { useUser } from "@auth0/nextjs-auth0/client";

const Home = () => {
  const { user, isLoading, error } = useUser();

  if (isLoading) <div className="text-center mt-10">Loading...</div>;
  if (error) <div className="text-center mt-10">{error.message}</div>;

  return (
    <>
      <nav className="bg-cyan-900 p-4">
        <div className="container mx-auto flex justify-between items-center">
          <Link href="/" className="text-white text-lg font-bold">
            Auth0 Tutorial
          </Link>
          <div className="flex items-center justify-between space-x-4">
            {user ? (
              <>
                <Link
                  href="/profile"
                  className="text-white text-lg font-medium mr-5"
                >
                  Profile
                </Link>
                <a
                  href="/api/auth/logout"
                  className="text-white text-lg font-medium"
                >
                  Logout
                </a>
              </>
            ) : (
              <a
                href="/api/auth/login"
                className="text-white text-lg font-medium"
              >
                Login
              </a>
            )}
          </div>
        </div>
      </nav>

      <div className="my-5 text-center">
        {!user ? (
          <>
            <h1 className="mb-4">Welcome to Next.js and Auth0 Tutorial</h1>

            <p className="lead">
              Log in to access your profile information (protected route)
            </p>
          </>
        ) : (
          <>
            <h1 className="mb-4">Welcome to Next.js and Auth0 Tutorial</h1>

            <p className="lead">
              This is a sample application that demonstrates an authentication
              flow for a Regular Web App, using{" "}
              <a href="https://auth0.com">Auth0</a> and{" "}
              <a href="https://nextjs.org">Next.js</a>
            </p>
          </>
        )}
      </div>
    </>
  );
};

export default Home;
