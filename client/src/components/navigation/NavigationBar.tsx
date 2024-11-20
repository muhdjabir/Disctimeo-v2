'use client'

import Link from "next/link"
import { Button } from "@/components/ui/button"
import { Sheet, SheetContent, SheetTitle, SheetTrigger } from "@/components/ui/sheet"
import { Menu } from 'lucide-react'

export default function NavigationBar({ pages }: { pages: { page: string; path: string }[] }) {
    return (
        <nav className="fixed inset-x-0 top-0 z-50 bg-white shadow-sm dark:bg-gray-950/90 h-14">
            <div className="w-full max-w-7xl mx-auto px-4">
                <div className="flex justify-between h-14 items-center">
                    <div className="flex items-center gap-2">
                        <Link href="/" className="flex items-center gap-2" prefetch={false}>
                            <img src="/blue_logo.png" className="h-12 w-12" />
                            <span className="sr-only">Acme Inc</span>
                            <h2 className="font-bold">Disctimeo</h2>
                        </Link>
                    </div>

                    {/* Desktop Navigation */}
                    <nav className="hidden md:flex gap-4 flex-1 justify-center">
                        {pages && pages.map((page) => (
                            <Link
                                key={page.page}
                                href={page.path}
                                className="font-medium flex items-center text-sm transition-colors hover:underline"
                                prefetch={false}
                            >
                                {page.page}
                            </Link>
                        ))}
                    </nav>

                    {/* Mobile Navigation */}
                    <Sheet>
                        <SheetTitle>
                            <SheetTrigger asChild className="md:hidden ml-auto">
                                <Button variant="outline" size="icon">
                                    <Menu className="h-6 w-6" />
                                    <span className="sr-only">Toggle navigation menu</span>
                                </Button>
                            </SheetTrigger>
                        </SheetTitle>
                        <SheetContent side="left" className="w-72">
                            <div className="flex flex-col space-y-3 px-2 py-6">
                                {pages && pages.map(page => <Link
                                    key={page.page}
                                    href={page.path}
                                    className="font-medium text-sm transition-colors hover:underline"
                                    prefetch={false}
                                >
                                    {page.page}
                                </Link>)}

                                <div className="pt-4 space-y-2">
                                    <Button className="w-full bg-sky-700">
                                        <a href="/api/auth/login">
                                            Join Us
                                        </a>
                                    </Button>
                                </div>
                            </div>
                        </SheetContent>
                    </Sheet>

                    {/* Desktop Sign In/Up Buttons */}
                    <div className="hidden md:flex items-center gap-4">
                        <Button className="bg-sky-700" size="sm">
                            <a href="/api/auth/login">
                                Join Us
                            </a>
                        </Button>
                    </div>
                </div>
            </div>
        </nav>
    )
}